#  Order Event Platform

An Event-Driven Microservice platform built with **Spring Boot 3 / Java 21**, **Apache Kafka (KRaft mode)**, and **PostgreSQL**.

---

## 🏗️ Architecture & Workflow

1. Client sends a `POST /api/v1/orders` request via Swagger UI / REST Client.
2. `OrderService` persists the order to **PostgreSQL**.
3. `OrderProducer` publishes an `OrderCreatedEvent` to the Kafka topic `order-created-events`.
4. `OrderConsumer` asynchronously listens to the topic and processes the incoming event (simulating stock reservation / notification).

---

##  Tech Stack

* **Java**: 21
* **Framework**: Spring Boot 4.x
* **Messaging**: Apache Kafka (KRaft) & Spring Kafka
* **Database**: PostgreSQL 15 & Spring Data JPA
* **Documentation**: OpenAPI 3 / Swagger UI
* **Utilities**: Lombok, Docker Compose

---

##  How to Run

### 1. Prerequisites
* Docker Desktop installed & running
* Java 21 & Maven installed

### 2. Start Infrastructure (Kafka & PostgreSQL)
```bash
docker compose up -d
