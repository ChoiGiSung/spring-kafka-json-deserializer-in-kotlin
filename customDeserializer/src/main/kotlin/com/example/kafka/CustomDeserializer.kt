package com.example.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.kafka.support.serializer.JsonDeserializer

class CustomDeserializer<T> : JsonDeserializer<T>(
    Jackson2ObjectMapperBuilder
        .json()
        .build<ObjectMapper>()
) {
}