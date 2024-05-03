package com.project.shopapp.services;

import com.project.shopapp.dtos.OrderDetailDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.OrderDetail;
import com.project.shopapp.responses.OrderDetailResponse;

import java.util.List;

public interface IOrderDetailService {

    OrderDetail createOrderDetail (OrderDetailDTO orderDetailDTO) throws DataNotFoundException;
    OrderDetail updateOrderDetail(Long id ,OrderDetailDTO orderDetailDTO) throws DataNotFoundException;
    OrderDetail getOrderDetail (Long id) throws DataNotFoundException;
    List<OrderDetail> findByOrderId (Long orderId);
    void deleteOrderDetail (Long id);

}
