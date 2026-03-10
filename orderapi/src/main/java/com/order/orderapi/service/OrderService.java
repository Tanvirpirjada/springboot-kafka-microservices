package com.order.orderapi.service;

import com.order.orderapi.entity.Order;
import com.order.orderapi.entity.STATUS;
import com.order.orderapi.event.OrderCreatedEvent;
import com.order.orderapi.kafka.OrderProducer;
import com.order.orderapi.model.OrderModel;
import com.order.orderapi.repo.OrderRepo;
import org.springframework.stereotype.Service;

@Service
public class OrderService {


    public final OrderRepo orderRepo;

    private final OrderProducer producer;

    public OrderService(OrderRepo orderRepo, OrderProducer producer){
        this.orderRepo = orderRepo;
        this.producer = producer;
    }

    public String createOrder(OrderModel model) {
        Order order=new Order();
        order.setPrice(model.price());
        order.setUserId(model.userId());
        order.setQuantity(model.quantity());
        order.setStatus(STATUS.INVENTORY_PENDING);
        order.setProductName(model.productName());
        order.setProductId(model.productId());
        Order saveOrder=orderRepo.save(order);

        OrderCreatedEvent  event=new OrderCreatedEvent();
        event.setOrderId(saveOrder.getId());
        event.setPrice(saveOrder.getPrice());
        event.setProductName(saveOrder.getProductName());
        event.setProductId(saveOrder.getProductId());
        event.setQuantity(saveOrder.getQuantity());
        producer.sendOrderCreatedEvent(event);
        return "Order Placed";
    }
}
