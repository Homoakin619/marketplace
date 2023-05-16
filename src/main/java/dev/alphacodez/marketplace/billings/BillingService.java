package dev.alphacodez.marketplace.billings;

import dev.alphacodez.marketplace.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingService {

    private final BillingRepository billingRepository;

    public void createBillingAddress(String address,String area,User user,String orderId) {
        BillingAddress billingAddress = new BillingAddress(address,area,user,orderId);
        billingRepository.save(billingAddress);
    }
}
