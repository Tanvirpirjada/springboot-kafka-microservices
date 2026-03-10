package com.order.orderapi.model;

public record OrderModel(Long userId, Long productId, String productName, Long quantity, String price) {
}
