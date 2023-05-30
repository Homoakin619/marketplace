package dev.alphacodez.marketplace.products;


import dev.alphacodez.marketplace.auth.AuthenticationService;
import dev.alphacodez.marketplace.config.Utility;
import dev.alphacodez.marketplace.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;


@RequiredArgsConstructor
@RequestMapping("api/v1/products")
@RestController
public class ProductController {
    private final ProductService productService;
    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<?> allProducts() {
        return ResponseEntity.ok(productService.fetchAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable("productId") Long productId) throws Exception {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

//    @PreAuthorize("hasRole('USER')")


//    @GetMapping("/products/")
//    public ResponseEntity<?> fetchProductsByPriceAndCategory(@RequestParam("category") String category,@RequestParam("price") Double price ) {
//        if (category != null && price != null){
//            return ResponseEntity.ok(productService.fetchProductsByCategoryAndPriceRange(category, price));
//        }
//        return ResponseEntity.ok(productService.fetchProductsByPriceRange(price));
//    }

//    @GetMapping("/products")
//    public ResponseEntity<?> fetchProductsByPriceRange(@RequestParam("price") Double price) {
//        return ResponseEntity.ok(productService.fetchProductsByPriceRange(price));
//    }

    @GetMapping("/products/category")
    public ResponseEntity<?> fetchProductsByCategory(@RequestParam String category) {
        return ResponseEntity.ok(productService.fetchProductsByCategory(category));
    }
}
