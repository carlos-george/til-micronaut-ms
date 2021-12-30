package br.com.til.api.config

import io.micronaut.core.async.publisher.Publishers
import io.micronaut.discovery.ServiceInstance
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.client.LoadBalancer
import io.micronaut.http.client.ProxyHttpClient
import io.micronaut.http.filter.OncePerRequestHttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import io.micronaut.http.uri.UriBuilder
import io.reactivex.rxjava3.core.Flowable
import org.reactivestreams.Publisher


@Filter("/**")
class GatewayFilter(
    private val serviceLoadBalancers: Map<String, LoadBalancer>,
    private val proxyHttpClient: ProxyHttpClient
) : OncePerRequestHttpServerFilter() {

    override fun doFilterOnce(request: HttpRequest<*>?, chain: ServerFilterChain?): Publisher<MutableHttpResponse<*>> {
        val serviceName: String = getService(request!!.path)

        if (serviceName != "") {
            val loadBalancer: LoadBalancer = serviceLoadBalancers.get(serviceName)!!
            return Flowable.fromPublisher(loadBalancer.select())
                .flatMap { serviceInstance ->
                    val finalRequest: MutableHttpRequest<*> = prepareRequestForTarget(request, serviceInstance)
                    println(
                        "Proxying ${request!!.path} to service ${serviceInstance.getId()} " +
                                "(${serviceInstance.getHost()}:${serviceInstance.getPort()}) " +
                                "as ${finalRequest.path}"
                    )
                    proxyHttpClient.proxy(finalRequest)
                }
        }

        return Publishers.just(HttpResponse.notFound("Service not found."))
    }

    private fun prepareRequestForTarget(
        request: HttpRequest<*>,
        serviceInstance: ServiceInstance
    ): MutableHttpRequest<*> {
        return request.mutate()
            .uri { uri: UriBuilder ->
                uri
                    .scheme("http")
                    .host(serviceInstance.host)
                    .port(serviceInstance.port)
                    .replacePath(request.path.replace("/" + serviceInstance.id, ""))
            }
    }

    private fun getService(path:String, ) : String {

        var service = ""
        path.split("/").forEach {
            if (serviceLoadBalancers.containsKey(it)) {
                service = it
            }

        }

        return service
    }

}
