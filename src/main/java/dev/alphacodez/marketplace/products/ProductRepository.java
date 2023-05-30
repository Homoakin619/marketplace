package dev.alphacodez.marketplace.products;

import dev.alphacodez.marketplace.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategory(String Category);

    @Query("SELECT p FROM Product p WHERE p.category =?1 AND p.price <= ?2")
    List<Product> findByCategoryAndPriceRange(String category, Double priceRange);

    @Query("SELECT p FROM Product p WHERE p.user =?1")
    List<Product> fetchAllUserProducts(User user);

    @Query("SELECT p FROM Product p WHERE p.price <=?1")
    List<Product> findByPriceRange(Double priceRange);
}
