package com.notification.notificationapi.event;

public class InventoryEvent {

    private Long orderId;

    private String email;
    private String status;

    public InventoryEvent(){ }

    public InventoryEvent(Long orderId,String email, String status) {
        this.orderId = orderId;
        this.email=email;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
