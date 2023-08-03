package com.example.fakeapi.business;

import com.example.fakeapi.apiv1.dto.ProductsDTO;
import com.example.fakeapi.exceptions.ProductException;
import com.example.fakeapi.infrastructure.entities.ProductEntity;
import com.example.fakeapi.infrastructure.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductsDTO save(ProductsDTO dto) {
        try {
            val entity = new ProductEntity(dto);
            val saved = productRepository.save(entity);
            return ProductsDTO.fromEntity(saved);
        } catch (Exception e) {
            throw new ProductException("Could not save the product", e, HttpStatus.BAD_REQUEST);
        }
    }

    public ProductEntity save(ProductEntity productEntity) {
        try {
            return productRepository.save(productEntity);
        } catch (Exception e) {
            throw new ProductException("Could not save the product", e, HttpStatus.BAD_REQUEST);
        }
    }

    public List<ProductEntity> saveAll(List<ProductEntity> productEntity) {
        try {
            return productRepository.saveAll(productEntity);
        } catch (Exception e) {
            throw new ProductException("Could not save the product", e, HttpStatus.BAD_REQUEST);
        }
    }

    public List<ProductsDTO> getAll() {
        try {
            return productRepository.findAll().stream().map(ProductsDTO::fromEntity).toList();
        } catch (Exception e) {
            throw new ProductException("Could not get the products", e, HttpStatus.NOT_FOUND);
        }
    }


    public ProductsDTO updateProduct(String id, ProductsDTO dto){
        try{
            ProductEntity entity = productRepository.findById(id)
                    .orElseThrow(() -> new ProductException("Product not found", HttpStatus.NOT_FOUND));
            val productEntity = new ProductEntity(dto);
            productEntity.setId(entity.getId());
            save(productEntity);
            return ProductsDTO.fromEntity(entity);
        }catch (Exception e) {
            throw new ProductException("Could not update the product", e, HttpStatus.BAD_REQUEST);
        }
    }

    public void deleteProduct(String title){
        try{
            productRepository.deleteByTitle(title);
        }catch (Exception e) {
            throw new ProductException("Could not delete the product", e, HttpStatus.BAD_REQUEST);
        }
    }

    public Boolean productExists(String title){
        try{
            return productRepository.existsByTitle(title);
        }catch (Exception e) {
            throw new ProductException("Could not delete the product", e, HttpStatus.BAD_REQUEST);
        }
    }

}
