package com.study.kafka.shopapi.dto;

import com.study.kafka.shopapi.model.ShopItem;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ShopItemDto {
    private String productIdentifier;
    private Integer amount;
    private Float price;

    public static ShopItemDto convert(ShopItem shopItemD){
        ShopItemDto shopItemDto = new ShopItemDto();
        shopItemDto.setProductIdentifier(shopItemD.getProductIdentifier());
        shopItemDto.setAmount(shopItemD.getAmount());
        shopItemDto.setPrice(shopItemD.getPrice());
        return shopItemDto;
    }
}
