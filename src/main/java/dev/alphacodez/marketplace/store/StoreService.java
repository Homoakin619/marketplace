package dev.alphacodez.marketplace.store;

import dev.alphacodez.marketplace.config.Utility;
import dev.alphacodez.marketplace.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository repository;
    private final Utility utility;

    public void createStore(StoreDto request) throws IOException,IllegalAccessException {
        User user = utility.getAuthenticatedUser();
        String url = "";
        if (user != null) {
            if (request.getImage() != null ) {
                MultipartFile image = request.getImage();
                url = utility.createImageUpload(image,"store-images");
            }

            var store = Store.builder()
                    .title(request.getTitle())
                    .address(request.getAddress())
                    .imageUrl(url)
                    .build();
            store.setUser(user);
            repository.save(store);
        }
    }

    public List<StoreResponseDto> getAllStores() {
        System.out.println("first");
        List<Store> rawValues = repository.findAll();
        System.out.println("second");
        List<StoreResponseDto> stores = new ArrayList<>();
        System.out.println("third");
        for(Store store : rawValues) {
            StoreResponseDto instance = new StoreResponseDto(store.getTitle(), store.getAddress(), store.getImageUrl());
            stores.add(instance);
        }
        System.out.println("fourth");
        System.out.println(stores.toString());
        return stores;
    }
}
