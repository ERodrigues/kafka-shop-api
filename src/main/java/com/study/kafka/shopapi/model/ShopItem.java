package com.study.kafka.shopapi.model;

import com.study.kafka.shopapi.dto.ShopItemDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "shop_item")
public class ShopItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="product_identifier")
    private String productIdentifier;
    @Column(name="amount")
    private Integer amount;
    @Column(name="price")
    private Float price;

    @ManyToOne
    @JoinColumn(name="shop_id")
    private Shop shop;

    public static ShopItem convert(ShopItemDto shopItemDto){
        ShopItem shopItem = new ShopItem();
        shopItem.setProductIdentifier(shopItemDto.getProductIdentifier());
        shopItem.setAmount(shopItemDto.getAmount());
        shopItem.setPrice(shopItemDto.getPrice());
        return shopItem;
    }
}
