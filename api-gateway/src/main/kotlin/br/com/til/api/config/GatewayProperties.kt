package br.com.til.api.config

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("gateway")
class GatewayProperties(
    var services: MutableSet<String>
)