package dev.alphacodez.marketplace.cart;

import dev.alphacodez.marketplace.products.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "order")
    private List<Product> products;
    private Boolean isCompleted = false;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;
    private Long quantity;
    private Double total;

    public Double getOrderTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice() + quantity;
        }
        return total;
    }

}
