package dev.alphacodez.marketplace.products;

import dev.alphacodez.marketplace.store.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String imageUrl;
//   to add later on:-- product category
    @Enumerated(EnumType.STRING)
    private ProductCategoryEnum category;
    private String description;
    @Column(nullable = false)
    private Double price;
    private boolean isDiscounted = false;
    private Long discountPrice;
    private boolean outOfStock = false ;
    @ManyToOne
    private Store store;
    

}