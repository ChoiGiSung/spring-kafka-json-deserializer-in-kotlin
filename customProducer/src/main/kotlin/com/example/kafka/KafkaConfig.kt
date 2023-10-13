package com.example.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ser.std.StringSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaConsumerFactoryCustomizer
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.converter.RecordMessageConverter
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfig(
    val objectMapper: ObjectMapper
) : DefaultKafkaProducerFactoryCustomizer {

    /**
     * TYPE_MAPPINGS을 커스텀 하는 방법 3가지
     * idClassMapping을 어떻게 넣어주냐의 차이.
     */



    /**
     * case 1 : producerFactory의 설정 업데이트. 어차피 JsonSerializer가 사용하는 값임.
     * 지금은 하드 코딩이지만 동적으로 발송될 Class의 path을 읽어서 value를 만들어 줄 수 있도록 개발할 수 있다.
     */
//    override fun customize(producerFactory: DefaultKafkaProducerFactory<*, *>?) {
//        (producerFactory as DefaultKafkaProducerFactory<Any, Any>).valueSerializer = JsonSerializer(objectMapper)
//        producerFactory.updateConfigs(mapOf(JsonSerializer.TYPE_MAPPINGS to "CREATE:class.~~~,UPDATE:class.~~~"))
//    }


    /**
     * case 2 : jsonSerializer의 typeMapper를 오버라이딩 한다.
     */
    override fun customize(producerFactory: DefaultKafkaProducerFactory<*, *>?) {
        val jsonSerializer = JsonSerializer<Any>(objectMapper)
        jsonSerializer.typeMapper = typeMapper()

        (producerFactory as DefaultKafkaProducerFactory<Any, Any>).valueSerializer = jsonSerializer
    }

    private fun typeMapper(): DefaultJackson2JavaTypeMapper {
        val typeMapper = DefaultJackson2JavaTypeMapper()
        val mappings: MutableMap<String, Class<*>> = mutableMapOf()
        mappings["CREATE"] = CustomProducer.UserInfo::class.java
        mappings["UPDATE"] = CustomProducer.OtherUserInfo::class.java
        typeMapper.typePrecedence = Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID
        typeMapper.idClassMapping = mappings
        return typeMapper
    }



    /**
     * case 3 : application.properties에서 주석 부분 작성
     */


}