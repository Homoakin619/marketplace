package dev.alphacodez.marketplace.store;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StoreDto {
    private String title;
    private String address;
    private MultipartFile image;
    private String imageUrl;

    public StoreDto(String title, String address, String imageUrl) {
        this.title = title;
        this.address = address;
        this.imageUrl = imageUrl;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public MultipartFile getImage() {
        return image;
    }
    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
