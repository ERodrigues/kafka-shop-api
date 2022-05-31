package com.study.kafka.shopapi.events;

import com.study.kafka.shopapi.dto.ShopDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaClient {
    private final KafkaTemplate<String, ShopDto> kafkaTemplate;
    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";

    public void sendMessage(ShopDto message){
        kafkaTemplate.send(SHOP_TOPIC_NAME, message);
    }
}
