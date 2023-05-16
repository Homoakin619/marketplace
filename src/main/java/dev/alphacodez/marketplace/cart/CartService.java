package dev.alphacodez.marketplace.cart;

import dev.alphacodez.marketplace.products.Product;
import dev.alphacodez.marketplace.products.ProductRepository;
import dev.alphacodez.marketplace.transactions.TransactionService;
import dev.alphacodez.marketplace.users.User;
import dev.alphacodez.marketplace.users.UserRepository;
import dev.alphacodez.marketplace.utilities.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class CartService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final TransactionService transactionService;

    public void orderItems(OrderItemDto[] items) {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            String username =  SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByEmail(username).orElseThrow(() -> new IllegalStateException("user not found"));
            Cart cart = createCartIfNotExist(user);
            for (OrderItemDto item : items) {
                Product product = productRepository.findById(item.getProductId())
                        .orElseThrow(() -> new IllegalStateException("product not found"));
                OrderItem order = new OrderItem(user,product,item.getQuantity());
                order.setCart(cart);
                itemRepository.save(order);
            }
        }
    }
    public Cart createCartIfNotExist(User user) {
        Optional<Cart> query = cartRepository.findByCompletedStatus(false,user);
        if (query.isEmpty()) {
            String orderId = IdGenerator.generateId();
             Cart cart = new Cart(user);
             cartRepository.save(cart);
             return cart;
        }
        return query.orElseThrow();
    }

    public void checkoutCart(User user){
        // TODO: include payment payload here
        String paymentId = ""; // get this from the payment payload
        Cart cart = cartRepository.findByCompletedStatus(false,user).orElseThrow();
        cart.setCompleted(true);
        cartRepository.save(cart);
        transactionService.createTransaction(cart.getOrderId(),paymentId);
    }

}
