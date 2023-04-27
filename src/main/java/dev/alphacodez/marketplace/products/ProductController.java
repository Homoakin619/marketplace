package dev.alphacodez.marketplace.products;


import dev.alphacodez.marketplace.config.Utility;
import dev.alphacodez.marketplace.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductDto request) throws IOException {

        return ResponseEntity.ok(productService.createProduct(request));
    }
}
