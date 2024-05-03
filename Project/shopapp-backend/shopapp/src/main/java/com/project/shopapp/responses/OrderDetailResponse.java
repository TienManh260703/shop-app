package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailResponse {
    private Long id;
    @JsonProperty(namespace = "order_id")
    private Long orderId;
    @JsonProperty(namespace = "product_id")
    private Long productId;
    private Float price;
    @JsonProperty(namespace = "number_of_products")
    private Integer numberOfProducts;
    @JsonProperty(namespace = "total_money")
    private Float totalMoney;
    private String color;
}
