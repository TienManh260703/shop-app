package com.project.shopapp.services;

import com.project.shopapp.dtos.OrderDTO;
import com.project.shopapp.dtos.ProductDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.Order;
import com.project.shopapp.responses.OrderResponse;
import com.project.shopapp.responses.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface IOrderService {

    Order createOder (OrderDTO orderDTO) throws Exception;
   Order getOrder (Long  id);
    Order updateOrder (Long id , OrderDTO orderDTO) throws DataNotFoundException;
    List<Order> findByUserId (Long userId);

    void deleteOrder (Long id) throws DataNotFoundException;
}
