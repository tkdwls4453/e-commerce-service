package com.hanghae.product.application;

import com.hanghae.product.application.port.ProductQueryRepository;
import com.hanghae.product.domain.dto.response.ProductPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductQueryRepository productQueryRepository;

    public ProductPageDto getAllProductInfo(Long cursor, Integer size){
        return productQueryRepository.findAllActiveByCursor(cursor, size);
    }
}
