package com.study.kafka.shopapi.controller;

import com.study.kafka.shopapi.dto.ShopDto;
import com.study.kafka.shopapi.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping
    public List<ShopDto> getShop(){
        return shopService.findAll();
    }

    @PostMapping
    public ShopDto saveShop(@RequestBody ShopDto shopDto){
        return shopService.save(shopDto);
    }
}
