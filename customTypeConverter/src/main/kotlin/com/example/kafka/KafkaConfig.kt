package com.example.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.support.converter.RecordMessageConverter
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper

@EnableKafka
@Configuration
class KafkaConfig(
    val objectMapper: ObjectMapper
) {

    @Bean
    fun multiTypeConverter(): RecordMessageConverter {
        val converter = StringJsonMessageConverter(objectMapper)
        val typeMapper = DefaultJackson2JavaTypeMapper()
        val mappings: MutableMap<String, Class<*>> = mutableMapOf()
        mappings["CREATE"] = ConsumerController.UserInfo::class.java
        mappings["UPDATE"] = ConsumerController.OtherUserInfo::class.java
        typeMapper.typePrecedence = Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID
        typeMapper.idClassMapping = mappings
        converter.typeMapper = typeMapper
        return converter
    }
}