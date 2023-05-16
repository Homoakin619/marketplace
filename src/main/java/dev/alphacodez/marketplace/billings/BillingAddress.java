package dev.alphacodez.marketplace.billings;

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
    private String orderId;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public BillingAddress(String address, String area, User user,String orderId) {
        this.address = address;
        this.area = area;
        this.user = user;
        this.orderId = orderId;
    }
}
