package com.example.kafka

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CustomProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    fun send(userInfo: UserInfo) {
        kafkaTemplate.send("testTopic", userInfo)
    }

    data class UserInfo(
        val name: String,
        val phoneNumber: String
    )
}