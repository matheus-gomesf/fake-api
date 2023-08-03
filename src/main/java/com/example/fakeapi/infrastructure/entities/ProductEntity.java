package com.example.fakeapi.infrastructure.entities;

import com.example.fakeapi.apiv1.dto.ProductsDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
public class ProductEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "product_id")
    private Long productId;
    @Column(name = "title", length = 800)
    private String title;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "category", length = 800)
    private String category;
    @Column(name = "description", length = 800)
    private String description;
    @Column(name = "image", length = 800)
    private String image;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    public ProductEntity(Long productId, String title, BigDecimal price, String category, String description, String image, LocalDateTime createdAt) {
        this.id = UUID.randomUUID();
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
        this.createdAt = createdAt;
    }

    public ProductEntity(ProductsDTO dto) {
        this.id = UUID.randomUUID();
        this.productId = dto.id();
        this.title = dto.title();
        this.price = dto.price();
        this.category = dto.category();
        this.description = dto.description();
        this.image = dto.image();
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        return productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return productId.hashCode();
    }
}
