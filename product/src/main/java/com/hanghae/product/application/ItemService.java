package com.hanghae.product.application;

import com.hanghae.product.application.port.ItemQueryRepository;
import com.hanghae.product.domain.dto.response.ItemProductDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemQueryRepository itemQueryRepository;


    public List<ItemProductDto> getItemProducts(List<Long> itemIds) {
        return itemQueryRepository.getItemProducts(itemIds);
    }
}
