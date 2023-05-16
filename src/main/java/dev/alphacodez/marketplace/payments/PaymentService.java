package dev.alphacodez.marketplace.payments;


import dev.alphacodez.marketplace.cart.Cart;
import dev.alphacodez.marketplace.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;


    public String makePayment(User user, Double amount, Cart cart){
        // TODO: Send payment payload to payment service (e.g: paystack,stripe...)
        // payment service returns a payload, save that payload into the payment entity
    var paymentInstance = Payment.builder()
            //.paymentId() unique transaction id from payment system payload;
            .date(LocalDateTime.now())
            .amount(amount)
            .cart(cart)
            .user(user)
            .build();
    paymentRepository.save(paymentInstance);
    // should return typical payment payload
    return "payment successful";
    }
}
