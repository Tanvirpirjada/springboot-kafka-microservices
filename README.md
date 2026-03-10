# Kafka Microservices Order Processing System

## Project Overview
This project demonstrates an event-driven microservices architecture using Spring Boot and Apache Kafka.

The system simulates an Order Processing workflow where services communicate asynchronously through Kafka topics.

## Services

### Order Service
Creates orders and publishes events to Kafka.

### Inventory Service
Consumes order events and checks product availability.

### Notification Service
Receives inventory results and sends notifications.

## Architecture

Order Service → Kafka → Inventory Service → Kafka → Notification Service

## Tech Stack

- Java
- Spring Boot
- Apache Kafka
- MySQL
- Maven
- REST APIs
- Lombok

## Kafka Topics

| Topic | Description |
|------|-------------|
| order-topic | Order created events |
| inventory-confirmed | Inventory available |
| inventory-failed | Inventory unavailable |

## Project Structure

```
kafka-microservices-project
│
├── order-service
├── inventory-service
├── notification-service
└── README.md
```

## Running the Project

1. Start Kafka and kraft (this is in new kafka version if you are using old one then u have zookeper for that)
2. Start Inventory Service
3. Start Notification Service
4. Start Order Service

## Example API Request

POST /create/orders

```
{
    "userId":1,
    "productId":1,
    "productName":"Laptop",
    "quantity":10,
    "price":"200000"
}
```


## 🚧 Future Improvements

The following enhancements are planned to extend the system into a more production-ready microservices architecture.

### 1. Payment Service
A dedicated **Payment Microservice** will be added to handle payment processing.

Responsibilities:
- Payment authorization
- Payment status events
- Integration with order workflow

Flow:

Order Service → Payment Service → Inventory Service → Notification Service

---

### 2. API Gateway & Authentication

The system will be extended with:

- **API Gateway** for centralized routing
- **Authentication Service** for secure access

Planned architecture:

Client → API Gateway → Microservices

Features:
- JWT based authentication
- Request routing
- Rate limiting
- Centralized security

---

### 3. UI Implementation

A frontend application will be developed to interact with the backend services.

Planned features:
- Order creation UI
- Order status tracking
- Inventory availability display
- Notification updates

The UI will communicate with the backend through the **API Gateway**.

---

### 4. Additional Enhancements

Future improvements may also include:

- Docker containerization
- Kafka monitoring
- Distributed logging
- Circuit breaker pattern
- Centralized configuration

## Author


Tanvir Pirjada  
Java Backend Developer
