package dev.alphacodez.marketplace.payments;


import dev.alphacodez.marketplace.cart.Cart;
import dev.alphacodez.marketplace.users.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private LocalDateTime date;
    private String paymentId;

    @OneToOne
    private Cart cart;

    @ManyToOne
    private User user;

}
