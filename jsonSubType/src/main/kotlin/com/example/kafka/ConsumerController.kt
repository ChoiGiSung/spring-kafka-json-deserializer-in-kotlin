package com.example.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ConsumerController {

    @KafkaListener(groupId = "sample-4", topics = ["testTopic"])
    fun consume(userInfo: UserInfo) {
        println(userInfo)
    }

}