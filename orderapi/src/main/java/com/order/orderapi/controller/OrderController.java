package com.order.orderapi.controller;


import com.order.orderapi.model.OrderModel;
import com.order.orderapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
   public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("create/order")
    public ResponseEntity<String> createOrder(@RequestBody OrderModel model){
        orderService.createOrder(model);
        return null;
    }

}
