package com.study.kafka.shopapi.events;

import com.study.kafka.shopapi.dto.ShopDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value(value = "${kafka.bootstrap.Address:172.24.121.220:9092}")
    private String bootStrapAddress;
    private Map<String, Object> properties;

    public ProducerFactory<String, ShopDto> producerFactory(){
        properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapAddress);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "shop-api");

        return new DefaultKafkaProducerFactory<>(properties);
    }
    
    @Bean
    public KafkaTemplate<String, ShopDto> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    public ConsumerFactory<String, ShopDto> consumerFactory(){
        JsonDeserializer<ShopDto> deserializer = new JsonDeserializer<>(ShopDto.class);
        properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapAddress);
        return new DefaultKafkaConsumerFactory<>(properties, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShopDto> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, ShopDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
