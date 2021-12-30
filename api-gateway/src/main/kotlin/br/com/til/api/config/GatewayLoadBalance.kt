package br.com.til.api.config

import io.micronaut.context.annotation.Factory
import io.micronaut.http.client.LoadBalancer
import io.micronaut.http.client.loadbalance.DiscoveryClientLoadBalancerFactory
import jakarta.inject.Singleton

@Factory
class GatewayLoadBalance {

    @Singleton
    fun serviceLoadBalancers(
        gatewayProperties: GatewayProperties,
        factory: DiscoveryClientLoadBalancerFactory
    ) : Map<String, LoadBalancer> {

        return gatewayProperties.services.associateWith { serviceName ->
            factory.create(serviceName)
        }
    }
}
