package dev.alphacodez.marketplace.cart;

import dev.alphacodez.marketplace.products.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class OrderItemDto {
    private Long productId;
    private Long quantity;
}



