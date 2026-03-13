# Event-Driven Order Processing System

A production-grade microservices architecture built with **Spring Boot** and **Apache Kafka**, featuring secure authentication, API Gateway routing, and fully asynchronous inter-service communication.

---

## Architecture

```
Client → API Gateway → Auth Service (JWT)
                     → Order Service → Kafka → Inventory Service → Kafka → Notification Service
```

All downstream service communication is **asynchronous via Kafka**. The API Gateway handles all incoming requests and enforces JWT-based authentication before routing.

---

## Services

### 🔐 Auth Service *(New)*
Handles user registration and login. Returns a signed JWT access token on successful authentication.

**Endpoints:**
- `POST /auth/signup` — Register a new user
- `POST /auth/login` — Authenticate and receive access token

**JWT Token contains:**
- `userId`
- `email`
- `userType`

### 🚦 API Gateway
Centralized entry point for all client requests. Validates JWT tokens and routes traffic to the appropriate downstream microservice.

### 📦 Order Service
Accepts authenticated order requests and publishes order events to Kafka.

### 🏭 Inventory Service
Consumes order events from Kafka and checks product availability. Publishes result events back to Kafka.

### 🔔 Notification Service
Consumes inventory result events and sends notifications based on order outcome.

---

## Tech Stack

- **Java & Spring Boot** — Core framework
- **Apache Kafka** — Async event-driven communication
- **Spring Security + JWT** — Authentication & authorization
- **API Gateway** — Centralized routing and security
- **MySQL** — Persistent storage
- **Docker** — Containerization
- **Maven** — Build tool
- **Lombok** — Boilerplate reduction
- **REST APIs** — Synchronous client-facing endpoints

---

## Kafka Topics

| Topic | Producer | Consumer | Description |
|-------|----------|----------|-------------|
| `order-topic` | Order Service | Inventory Service | New order created |
| `inventory-confirmed` | Inventory Service | Notification Service | Stock available |
| `inventory-failed` | Inventory Service | Notification Service | Stock unavailable |

---

## Project Structure

```
springboot-kafka-microservices
│
├── auth-service
├── api-gateway
├── order-service
├── inventory-service
├── notification-service
└── README.md
```

---

## Running the Project

1. Start Kafka (KRaft mode for newer Kafka versions, ZooKeeper for older)
2. Start **Auth Service**
3. Start **API Gateway**
4. Start **Inventory Service**
5. Start **Notification Service**
6. Start **Order Service**

---

## API Reference

### Sign Up
```
POST /auth/signup

{
  "email": "tanvir@example.com",
  "password": "securepassword",
  "userType": "CUSTOMER"
}
```

### Login
```
POST /auth/login

{
  "email": "tanvir@example.com",
  "password": "securepassword"
}

Response:
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "userId": 1,
  "email": "tanvir@example.com",
  "userType": "CUSTOMER"
}
```

### Create Order *(requires Bearer token)*
```
POST /create/orders
Authorization: Bearer <accessToken>

{
  "userId": 1,
  "productId": 1,
  "productName": "Laptop",
  "quantity": 10,
  "price": "200000"
}
```

---

## 🚧 Planned Improvements

### Payment Service
A dedicated payment microservice to handle payment authorization and integrate into the order workflow.

**Planned flow:**
Order Service → Payment Service → Inventory Service → Notification Service

### UI Frontend
A frontend application for order creation, status tracking, inventory display, and notification updates — communicating via the API Gateway.

### Additional Enhancements
- Kafka monitoring dashboard
- Distributed logging (ELK stack)
- Circuit breaker pattern (Resilience4j)
- Centralized configuration (Spring Cloud Config)

---

## Author

**Mohammed Tanvir**
Java Full Stack Developer
[GitHub](https://github.com/Tanvirpirjada) • [LinkedIn](https://linkedin.com/in/Mohammedtanvir)
