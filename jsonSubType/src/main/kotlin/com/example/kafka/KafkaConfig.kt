package com.example.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.support.converter.JsonMessageConverter

@Configuration
@EnableKafka
class KafkaConfig(
    val objectMapper: ObjectMapper
) {

    @Bean
    fun recodeConverter() = JsonMessageConverter(objectMapper)
}