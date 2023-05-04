package dev.alphacodez.marketplace.cart;

import dev.alphacodez.marketplace.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class BillingAddress {
    private String address;
    private String area;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
