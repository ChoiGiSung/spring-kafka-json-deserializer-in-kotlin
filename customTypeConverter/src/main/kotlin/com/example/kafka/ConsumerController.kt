package com.example.kafka

import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
@KafkaListener(groupId = "sample-3", topics = ["testTopic"])
class ConsumerController {

    @KafkaHandler
    fun consume(userInfo: UserInfo) {
        println(userInfo)
    }

    data class UserInfo(
        val name: String,
        val phoneNumber: String
    )
}