package dev.alphacodez.marketplace.cart;

import dev.alphacodez.marketplace.users.User;
import lombok.Data;

@Data
public class CartDto {
    private Long id;
    private Long orderId;
    private String user;
    private OrderStatusEnum orderStatus;
    private Double orderTotal;

}
