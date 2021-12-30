package br.com.til.adapters.redis.config

import io.micronaut.context.annotation.Property
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

object RedisConnection{

    fun getConnection(): Jedis {
        val jedisPool = JedisPool(JedisPoolConfig(), "veiculo-service_redis_1", 6379)
        return jedisPool.resource
    }
}
