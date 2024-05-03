package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    @NotNull(message = "Category is name cannot be empty")
    @Size(min = 3 , max = 100 , message =  "Tile must be between 3 and 200 characters")
    private String name;
    @Min(value =   0 , message = "Price must br greater than or equal to 0")
    @Max(value =  10000000 , message = "Price must greater than or equal to 10,000,000")
    private Float price;
    private String thumbnail;
    private String description;
    @JsonProperty("category_id")
    private Long categoryId;
//    private MultipartFile file;

//    Upload nhiều ảnh
//    private List<MultipartFile> files;
}
