package dev.alphacodez.marketplace.products;

import dev.alphacodez.marketplace.config.Utility;
import dev.alphacodez.marketplace.store.Store;
import dev.alphacodez.marketplace.store.StoreRepository;
import dev.alphacodez.marketplace.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final Utility utility;
    private final StoreRepository storeRepository;

    public String createProduct(ProductDto request) throws IOException {
        MultipartFile image = request.getImage();
        User user = utility.getAuthenticatedUser();
        Store store = user.getStore();
        String url = utility.createImageUpload(image,"product-images");

        var product = Product.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .store(store)
                .imageUrl(url)
                .build();
        repository.save(product);
        return "Product created Successfully";
    }

    public List<ProductDto> fetchAllProducts() {
        List<Product> products = repository.findAll();
        List<ProductDto> result = new ArrayList<>();
        for (Product product : products) {
            ProductDto instance = new ProductDto(product.getTitle(),product.getImageUrl(),
                    product.getDescription(),product.getPrice());
            result.add(instance);
        }
        return result;
    }
}
