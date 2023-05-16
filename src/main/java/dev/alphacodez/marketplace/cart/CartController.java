package dev.alphacodez.marketplace.cart;

import dev.alphacodez.marketplace.transactions.Transaction;
import dev.alphacodez.marketplace.transactions.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
@RestController
public class CartController {

    private final CartService cartService;
    private final TransactionService transactionService;

    @PostMapping("/add")
    public String orderItem(@RequestBody OrderItemDto[] orderItems) throws Exception {
        try {
            cartService.orderItems(orderItems);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return "Successful Operation";
    }

    @GetMapping("/transactions")
    public List<Transaction> fetchAllTransactions() {
        return transactionService.fetchAllTransactions();
    }

}
