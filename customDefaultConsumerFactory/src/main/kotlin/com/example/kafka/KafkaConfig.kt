package com.example.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaConsumerFactoryCustomizer
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer

@EnableKafka
@Configuration
class KafkaConfig(
    val objectMapper: ObjectMapper
) : DefaultKafkaConsumerFactoryCustomizer {

    override fun customize(consumerFactory: DefaultKafkaConsumerFactory<*, *>?) {
        (consumerFactory as DefaultKafkaConsumerFactory<Any, Any>).setValueDeserializer(
            ErrorHandlingDeserializer(JsonDeserializer(objectMapper))
        )
    }
}