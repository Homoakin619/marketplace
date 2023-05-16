package dev.alphacodez.marketplace.cart;

import dev.alphacodez.marketplace.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("SELECT c FROM Cart c WHERE c.isCompleted =?1 AND c.user = ?2 ")
    Optional<Cart> findByCompletedStatus(boolean status, User user);
}
