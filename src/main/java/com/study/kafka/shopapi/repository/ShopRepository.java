package com.study.kafka.shopapi.repository;

import com.study.kafka.shopapi.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findByIdentifier(String identifier);
}
