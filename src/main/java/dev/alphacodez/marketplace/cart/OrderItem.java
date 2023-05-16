package dev.alphacodez.marketplace.cart;

import dev.alphacodez.marketplace.products.Product;
import dev.alphacodez.marketplace.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public OrderItem(User user, Product product, Long quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

    @ManyToOne
    private Product product;

    @ManyToOne
    private Cart cart;

    private Boolean isCompleted = false;
    private Long quantity;


    public Double getOrderTotal() {
        return quantity * product.getPrice();
    }

}
