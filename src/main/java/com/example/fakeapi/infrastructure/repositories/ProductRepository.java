package com.example.fakeapi.infrastructure.repositories;

import com.example.fakeapi.infrastructure.entities.ProductEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Transactional
    void deleteByTitle(String nome);

    Boolean existsByTitle(String title);
}
