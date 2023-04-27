package dev.alphacodez.marketplace.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoreResponseDto {
    private String title;
    private String address;
    private String image;
}
