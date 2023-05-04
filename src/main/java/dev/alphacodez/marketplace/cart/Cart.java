package dev.alphacodez.marketplace.cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderId;
    @OneToMany(mappedBy = "cart")
    private List<Order> orders;
    private boolean isCompleted = false;
    private Double cartTotal;

    public double getCartTotal(){
        double total = 0;
        for (Order order: orders) {
            total += order.getOrderTotal();
        }
        return total;
    }

}
