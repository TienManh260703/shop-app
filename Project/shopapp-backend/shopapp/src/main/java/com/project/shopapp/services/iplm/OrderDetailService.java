package com.project.shopapp.services.iplm;

import com.project.shopapp.convert.ConvertOrderDetail;
import com.project.shopapp.dtos.OrderDetailDTO;
import com.project.shopapp.exceptions.DataNotFoundException;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderDetail;
import com.project.shopapp.models.Product;
import com.project.shopapp.repositories.OrderDetailRepository;
import com.project.shopapp.repositories.OrderRepository;
import com.project.shopapp.repositories.ProductRepository;
import com.project.shopapp.responses.OrderDetailResponse;
import com.project.shopapp.services.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ConvertOrderDetail convertOrderDetail;

    @Override
    public OrderDetail createOrderDetail(OrderDetailDTO orderDetailDTO) throws DataNotFoundException {
        Order existingOrder = orderRepository.findById(orderDetailDTO.getOrderId()).orElseThrow(() ->
                new DataNotFoundException(
                        "Cannot find Order with id :" + orderDetailDTO.getOrderId()));
        Product existingProduct = productRepository.findById(orderDetailDTO.getProductId()).orElseThrow(() ->
                new DataNotFoundException("Cannot find Product with id :" + orderDetailDTO.getProductId()));
        OrderDetail orderDetail = OrderDetail
                .builder()
                .order(existingOrder)
                .product(existingProduct)
                .numberOfProducts(orderDetailDTO.getNumberOfProducts())
                .totalMoney(orderDetailDTO.getTotalMoney())
                .price(orderDetailDTO.getPrice())
                .color(orderDetailDTO.getColor())
                .build();
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail updateOrderDetail(Long id, OrderDetailDTO orderDetailDTO) throws DataNotFoundException {
        OrderDetail existingOrderDetail = orderDetailRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException("Cannot find OrderDetail with id : " + id));
        Order existingOrder = orderRepository.findById(orderDetailDTO.getOrderId()).orElseThrow(()->
                new DataNotFoundException("Cannot find Order with id : "+orderDetailDTO.getOrderId()));
        Product existingProduct = productRepository.findById(orderDetailDTO.getProductId()).orElseThrow(()->
                new DataNotFoundException("Cannot find Product with id : "+ orderDetailDTO.getProductId()));
        existingOrderDetail.setOrder(existingOrder);
        existingOrderDetail.setProduct(existingProduct);
        existingOrderDetail.setColor(orderDetailDTO.getColor());
        existingOrderDetail.setTotalMoney(orderDetailDTO.getTotalMoney());
        existingOrderDetail.setNumberOfProducts(orderDetailDTO.getNumberOfProducts());
        existingOrderDetail.setPrice(orderDetailDTO.getPrice());
        return orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    public OrderDetail getOrderDetail(Long id) throws DataNotFoundException {
        return orderDetailRepository.findById(id)
                .orElseThrow(() ->
                        new DataNotFoundException("Cannot find OrderDetail with id : " + id));
    }

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    @Override
    public void deleteOrderDetail(Long id) {
        OrderDetail existingOrderDetail = orderDetailRepository.findById(id).orElse(null);
//        assert existingOrderDetail != null;
        if (existingOrderDetail != null) {
            orderDetailRepository.delete(existingOrderDetail);
        }
    }
}
