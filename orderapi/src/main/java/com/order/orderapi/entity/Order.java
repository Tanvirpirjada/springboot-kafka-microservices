package com.order.orderapi.entity;

import jakarta.persistence.*;
import lombok.*;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Orders")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long userId;

    private String productName;

    private  Long quantity;

    private String price;


    @Enumerated(EnumType.STRING)
    private STATUS status;

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }
}
