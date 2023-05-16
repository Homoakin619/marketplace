package dev.alphacodez.marketplace.billings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<BillingAddress,Long> {
}
