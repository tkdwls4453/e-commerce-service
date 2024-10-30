package com.hanghae.product.infrastructure.repository;

import com.hanghae.product.application.port.ItemQueryRepository;
import com.hanghae.product.domain.dto.response.ItemProductDto;
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
}
