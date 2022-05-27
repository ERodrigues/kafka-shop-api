package com.study.kafka.shopapi.service;

import com.study.kafka.shopapi.dto.ShopDto;
import com.study.kafka.shopapi.model.Shop;
import com.study.kafka.shopapi.model.ShopItem;
import com.study.kafka.shopapi.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
    private final KafkaClient kafkaClient;

    public List<ShopDto> findAll(){
        return shopRepository
                .findAll()
                .stream()
                .map(shop -> ShopDto.convert(shop))
                .collect(Collectors.toList());
    }

    public ShopDto save(ShopDto shopDto){
        shopDto.setIdentifier(UUID.randomUUID().toString());
        shopDto.setDateShop(LocalDate.now());
        shopDto.setStatus("PENDING");
        Shop shop = Shop.convert(shopDto);

        for (ShopItem shopItem: shop.getItems()){
            shopItem.setShop(shop);
        }

        shopDto = ShopDto.convert(shopRepository.save(shop));
        kafkaClient.sendMessage(shopDto);
        return shopDto;
    }
}
