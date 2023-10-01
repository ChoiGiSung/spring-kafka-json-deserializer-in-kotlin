package com.example.kafka

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KafkaApplicationTests {

	@Autowired
	lateinit var producer: CustomProducer

	@Test
	fun something() {
		producer.send(CustomProducer.UserInfo("홍길동", "010-1234-5678", "홍홍"))
	}

	@Test
	fun something2() {
		producer.send(CustomProducer.OtherInfo("홍길동", 12))
	}
}
