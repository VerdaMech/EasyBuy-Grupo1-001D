package com.easybuy.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;


@Entity
@Table(name = "ProductCategory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {

    @Id
    private Long productId;

    @Id
    private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_category_id", insertable = false, updatable = false)
    private Category category;
}
