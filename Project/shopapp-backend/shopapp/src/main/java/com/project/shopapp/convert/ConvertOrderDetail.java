package com.project.shopapp.convert;

import com.project.shopapp.models.OrderDetail;
import com.project.shopapp.responses.OrderDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class ConvertOrderDetail {

    public OrderDetailResponse convertOrderDetailToOrderDetailResponse(OrderDetail orderDetail) {
        return OrderDetailResponse
                .builder()
                .id(orderDetail.getId())
                .orderId(orderDetail.getOrder().getId())
                .productId(orderDetail.getProduct().getId())
                .color(orderDetail.getColor())
                .price(orderDetail.getPrice())
                .totalMoney(orderDetail.getTotalMoney())
                .numberOfProducts(orderDetail.getNumberOfProducts())
                .build();
    }
}
