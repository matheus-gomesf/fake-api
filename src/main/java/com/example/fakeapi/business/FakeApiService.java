package com.example.fakeapi.business;

import com.example.fakeapi.apiv1.dto.ProductsDTO;
import com.example.fakeapi.infrastructure.client.FakeApiClient;
import com.example.fakeapi.infrastructure.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FakeApiService {

    private final FakeApiClient fakeApiClient;
    private final ProductService productService;

    public List<ProductsDTO> getAll() {
        val productsDTOS = fakeApiClient.getAll();
        val productEntities = productsDTOS.stream().filter(product -> !productService.productExists(product.title())).map(ProductEntity::new).toList();
        productService.saveAll(productEntities);
        return productsDTOS;
    }
}
