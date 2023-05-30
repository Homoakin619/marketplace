package dev.alphacodez.marketplace.products;

import dev.alphacodez.marketplace.config.Utility;
import dev.alphacodez.marketplace.store.Store;
import dev.alphacodez.marketplace.store.StoreRepository;
import dev.alphacodez.marketplace.users.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final Utility utility;
    private final StoreRepository storeRepository;

    public String createProduct(ProductDto request) throws IOException {
        MultipartFile image;
        String url = "";

        if (!request.getImage().isEmpty()) {
            image = request.getImage();
            System.out.println(image);
            url = utility.createImageUpload(image,"product-images");
        }

        User user = utility.getAuthenticatedUser();
        Store store = user.getStore();

        var product = Product.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .store(store)
                .imageUrl(url)
                .user(user)
                .build();
        repository.save(product);
        return "Product created Successfully";
    }

    @Transactional
    public void editProduct(Long productId,ProductDto newProductData) throws Exception {
        Product product = repository.findById(productId)
                .orElseThrow(() -> new IllegalAccessException("Product with id does not exist"));
        if(newProductData.getTitle() != null
                && newProductData.getTitle().length() > 0
                && !Objects.equals(newProductData.getTitle(),product.getTitle())){
            product.setTitle(newProductData.getTitle());
        }

        if(newProductData.getDescription() != null
                && newProductData.getDescription().length() > 0
                && !Objects.equals(newProductData.getDescription(),product.getDescription())){
            product.setDescription(newProductData.getDescription());
        }

        if(newProductData.getPrice() != null
                && newProductData.getPrice() != 0
                && !Objects.equals(newProductData.getPrice(),product.getPrice())){
            product.setPrice(newProductData.getPrice());
        }

        if (newProductData.getImage() != null && !newProductData.getImage().isEmpty()){
            MultipartFile image = newProductData.getImage();
            utility.createImageUpload(image,"product-images");
        }
    }

    public List<ProductDto> convertProductToDto(@NotNull List<Product> products) {
        List<ProductDto> result = new ArrayList<>();
        for (Product product : products) {
            ProductDto instance = new ProductDto(product.getId(),product.getTitle(),product.getImageUrl(),
                    product.getDescription(),product.getPrice());
            result.add(instance);
        }
        return result;
    }

    public ProductDto convertProductToDto(@NotNull Product product) {
        return new ProductDto(product.getId(),product.getTitle(),product.getImageUrl(),
                product.getDescription(),product.getPrice());
    }


    public List<ProductDto> fetchAllProducts() {
        List<Product> products = repository.findAll();
        return convertProductToDto(products);
    }

    public List<ProductDto> fetchAllStaffProducts() {
        List<Product> products = repository.findAll();
        return convertProductToDto(products);
    }

    public List<ProductDto> fetchProductsByCategory(String category) {
        List<Product> result = repository.findByCategory(category);
        return convertProductToDto(result);
    }


    public List<ProductDto> fetchProductsByPriceRange(Double priceRange) {
        List<Product> result = repository.findByPriceRange(priceRange);
        return convertProductToDto(result);
    }

    public List<ProductDto> fetchProductsByCategoryAndPriceRange(String category, Double priceRange) {
        List<Product> result = repository.findByCategoryAndPriceRange(category,priceRange);
        return convertProductToDto(result);
    }

    public ProductDto getProduct(Long productId) throws Exception {
        Product query = repository.findById(productId).orElseThrow(() -> new IllegalAccessException("Product does not exist"));
        return convertProductToDto(query);
    }
}
