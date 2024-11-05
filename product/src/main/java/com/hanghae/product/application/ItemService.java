package com.hanghae.product.application;

import com.hanghae.product.application.port.ItemCommandRepository;
import com.hanghae.product.application.port.ItemQueryRepository;
import com.hanghae.product.domain.dto.response.ItemProductDto;
import com.hanghae.product.exception.InsufficientStockException;
import com.hanghae.product.presentation.dto.request.ReduceStockRequest.Info;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemQueryRepository itemQueryRepository;
    private final ItemCommandRepository itemCommandRepository;

    @Transactional(readOnly = true)
    public List<ItemProductDto> getItemProducts(List<Long> itemIds) {
        return itemQueryRepository.getItemProducts(itemIds);
    }

    public void reduceStock(List<Info> infos) {
        for(Info info : infos) {
            Integer stock = itemQueryRepository.getStock(info.getItemId());

            if (stock < info.getQuantity()) {
                throw new InsufficientStockException();
            }

            itemCommandRepository.reduceStock(info.getItemId(), info.getQuantity());
        }
    }
}
