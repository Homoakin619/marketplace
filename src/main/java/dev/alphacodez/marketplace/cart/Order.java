package dev.alphacodez.marketplace.cart;

import dev.alphacodez.marketplace.products.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Order {
    @OneToMany(mappedBy = "order")
    private List<Product> products;
    private Boolean isCompleted = false;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Cart cart;
    private Long quantity;
    private Double total;

    public Double getOrderTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice() + quantity;
        }
        return total;
    }

}
