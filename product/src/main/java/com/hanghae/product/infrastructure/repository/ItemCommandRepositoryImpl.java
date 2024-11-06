package com.hanghae.product.infrastructure.repository;

import com.hanghae.product.application.port.ItemCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ItemCommandRepositoryImpl implements ItemCommandRepository {

    private final ItemCommandJpaRepository itemCommandJpaRepository;


    @Override
    public void reduceStock(Long itemId, Integer quantity) {
        itemCommandJpaRepository.reduceStock(itemId, quantity);
    }

    @Override
    public void increaseStock(Long itemId, Integer quantity) {
        itemCommandJpaRepository.increaseStock(itemId, quantity);
    }
}
