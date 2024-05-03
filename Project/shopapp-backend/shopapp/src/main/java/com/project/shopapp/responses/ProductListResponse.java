package com.project.shopapp.responses;

import com.project.shopapp.models.Product;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class ProductListResponse {
    private List<ProductResponse> products;
    private int totalPage;
}
