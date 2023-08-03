package com.example.fakeapi.apiv1.dto;

import com.example.fakeapi.infrastructure.entities.ProductEntity;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductsDTO(
        Long id,
        String title,
        BigDecimal price,
        String category,
        String description,
        String image
) {

    public static ProductsDTO fromEntity(ProductEntity entity) {
        return ProductsDTO.builder()
                .id(entity.getProductId())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .category(entity.getCategory())
                .description(entity.getDescription())
                .image(entity.getImage())
                .build();
    }
}
