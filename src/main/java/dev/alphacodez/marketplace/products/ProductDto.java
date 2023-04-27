package dev.alphacodez.marketplace.products;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    public ProductDto(String title, String imageUrl, String description, Double price) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
    }

    private String title;
    private String imageUrl;
    private MultipartFile image;
    private String description;
    private Double price;
    private boolean isDiscounted;
    private Long discountPrice;
}
