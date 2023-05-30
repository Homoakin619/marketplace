package dev.alphacodez.marketplace.products;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/staff/product")
public class StaffProductController {
    private final ProductService productService;

    @PreAuthorize("hasRole('STAFF')")
    @GetMapping
    public ResponseEntity<?> fetchAllStaffProduct() {
        return ResponseEntity.ok(productService.fetchAllStaffProducts());
    }


    @PreAuthorize("hasRole('STAFF')")
    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductDto request) throws Exception {
        return ResponseEntity.status(HttpStatusCode.valueOf(201))
                .body(productService.createProduct(request));
    }


    @PreAuthorize("hasRole('STAFF')")
    @PutMapping("/edit/{productId}")
    public ResponseEntity<?> editProduct(@PathVariable("productId") Long productId, @ModelAttribute ProductDto product) throws Exception {
        productService.editProduct(productId,product);
        return ResponseEntity.ok("Product Updated Successfully");
    }

}
