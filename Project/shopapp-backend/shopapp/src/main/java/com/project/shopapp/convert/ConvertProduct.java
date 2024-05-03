package com.project.shopapp.convert;

import com.project.shopapp.models.Product;
import com.project.shopapp.responses.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ConvertProduct {

    public ProductResponse convertProductToProductResponse (Product product){
        ProductResponse response = ProductResponse
                .builder()
                .id(product.getId())
                .thumbnail(product.getThumbnail())
                .price(product.getPrice())
                .name(product.getName())
                .description(product.getDescription())
                .categoryId(product.getCategoryId().getId())
                .build();
        response.setUpdatedAt(product.getUpdatedAt());
        response.setCreatedAt(product.getCreatedAt());
        return response;
    }
}
