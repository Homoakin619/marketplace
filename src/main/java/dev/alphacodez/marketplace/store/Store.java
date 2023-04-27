package dev.alphacodez.marketplace.store;

import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.alphacodez.marketplace.products.Product;
import dev.alphacodez.marketplace.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.cloudinary.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String address;
    private String fbHandle;
    private String twitterHandle;
    private String IGHandle;
    private String imageUrl;

    @Transient
    private MultipartFile Image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "store")
    private List<Product> products;

    public void setImage(MultipartFile image) {
        this.Image = image;
    }
    
    public void uploadImage(Cloudinary cloudinary) throws IOException {
        if(Image != null && !Image.isEmpty()) {
            String filename = Image.getOriginalFilename();
            String contentType = Image.getContentType();
            byte[] imgBytes = Image.getBytes();

            Map<String,Object> uploadResponse = cloudinary.uploader().upload(imgBytes, ObjectUtils.asMap(
                    "folder","profile_images",
                    "use_filename" , "true",
                    "resource_type", "image"
                    ));
            imageUrl = (String) uploadResponse.get("secure_url");
        }
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", fbHandle='" + fbHandle + '\'' +
                ", twitterHandle='" + twitterHandle + '\'' +
                ", IGHandle='" + IGHandle + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", products=" + products +
                '}';
    }


}