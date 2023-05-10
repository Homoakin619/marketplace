package dev.alphacodez.marketplace.cart;

import dev.alphacodez.marketplace.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class BillingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String area;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
