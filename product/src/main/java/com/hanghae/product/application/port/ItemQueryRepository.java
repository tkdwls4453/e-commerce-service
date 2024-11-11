package com.hanghae.product.application.port;

import com.hanghae.product.domain.Item;
import com.hanghae.product.domain.dto.response.ItemProductDto;
import java.util.List;

public interface ItemQueryRepository {

    List<ItemProductDto> getItemProducts(List<Long> itemIds);

    Integer getStock(Long itemId);

    List<Item> findAll();
}
