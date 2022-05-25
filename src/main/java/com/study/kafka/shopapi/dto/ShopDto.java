package com.study.kafka.shopapi.dto;

import com.study.kafka.shopapi.model.Shop;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ShopDto {
    private String identifier;
    private String status;
    private LocalDate dateShop;
    private List<ShopItemDto> items = new ArrayList<>();

    public static ShopDto convert (Shop shop){
        ShopDto shopDto = new ShopDto();
        shopDto.setIdentifier(shop.getIdentifier());
        shopDto.setStatus(shop.getStatus());
        shopDto.setDateShop(shop.getDateShop());
        shopDto.setItems(shop.getItems().stream().map(item -> ShopItemDto.convert(item)).collect(Collectors.toList()));
        return shopDto;
    }
}
