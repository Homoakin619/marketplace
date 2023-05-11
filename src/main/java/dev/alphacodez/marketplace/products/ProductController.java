package dev.alphacodez.marketplace.products;


import dev.alphacodez.marketplace.config.Utility;
import dev.alphacodez.marketplace.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;


@RequiredArgsConstructor
@RequestMapping("api/v1/product")
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> allProducts() {
        return ResponseEntity.ok(productService.fetchAllProducts());
    }

//    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductDto request) throws IOException {
        return ResponseEntity.ok(productService.createProduct(request));
    }

//    @GetMapping("/products")
//    public ResponseEntity<?> fetchProductsByPriceAndCategory(@RequestParam("category") String category,@RequestParam("price") Double price ) {
//        if (category!=null && price != null){
//            return ResponseEntity.ok(productService.fetchProductsByCategoryAndPriceRange(category, price));
//        }
//        return ResponseEntity.ok(productService.fetchProductsByPriceRange(price));
//    }

    @GetMapping("/products")
    public ResponseEntity<?> fetchProductsByPriceRange(@RequestParam("price") Double price) {
        return ResponseEntity.ok(productService.fetchProductsByPriceRange(price));
    }

    @GetMapping("/products/category")
    public ResponseEntity<?> fetchProductsByCategory(@RequestParam String category) {
        return ResponseEntity.ok(productService.fetchProductsByCategory(category));
    }
}
