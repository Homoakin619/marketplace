package dev.alphacodez.marketplace.cart;

import dev.alphacodez.marketplace.payments.Payment;
import dev.alphacodez.marketplace.users.User;
import dev.alphacodez.marketplace.utilities.IdGenerator;
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

    public Cart(User user) {
        this.orderId = IdGenerator.generateId();
        this.user = user;
    }

    private String orderId;

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "cart")
    private Payment payment;

    @OneToMany(mappedBy = "cart")
    private List<OrderItem> items;

    private boolean isCompleted = false;

    public double getCartTotal(){
        double total = 0;
        for (OrderItem order: items) {
            total += order.getOrderTotal();
        }
        return total;
    }

}
