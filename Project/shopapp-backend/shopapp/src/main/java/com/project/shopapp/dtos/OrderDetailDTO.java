package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    @JsonProperty("order_id")
    @Min(value = 1 , message = "Order is ID must be > 0")
    private Long orderId;

    @Min(value = 1 , message = "Product is ID must be > 0")
    @JsonProperty("product_id")
    private Long productId;

    @Min(value = 0 , message = "Price is ID must be >= 0")
    private Float price;

    @Min(value = 1 , message = "Number OF Product must be >= 1")
    @JsonProperty("number_of_products")
    private int numberOfProducts;

    @Min(value = 0 , message = "Total money must be > 0")
    @JsonProperty("total_money")
    private Float totalMoney;

    private String color;
}
