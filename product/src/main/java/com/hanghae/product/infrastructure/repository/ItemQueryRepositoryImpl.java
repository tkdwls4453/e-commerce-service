package com.hanghae.product.infrastructure.repository;

import com.hanghae.product.application.port.ItemQueryRepository;
import com.hanghae.product.domain.Item;
import com.hanghae.product.domain.dto.response.ItemProductDto;
import com.hanghae.product.infrastructure.ItemEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ItemQueryRepositoryImpl implements ItemQueryRepository {

    private final ItemJpaQueryRepository itemJpaQueryRepository;

    @Override
    public List<ItemProductDto> getItemProducts(List<Long> itemIds) {
        return itemJpaQueryRepository.getItemProducts(itemIds);
    }

    @Override
    public ItemProductDto getItemProduct(Long itemId) {
        return itemJpaQueryRepository.getItemProduct(itemId);
    }

    @Override
    public Integer getStock(Long itemId) {
        return itemJpaQueryRepository.getStock(itemId);
    }

    @Override
    public List<Item> findAll() {
        return itemJpaQueryRepository.findAll().stream().map(ItemEntity::toDomain).toList();
    }




}
