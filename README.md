# Spring Boot Microservices Project

A **Spring Boot Microservices-based application** demonstrating service discovery, API Gateway routing, and communication between independent backend services.

## 🏗️ Project Architecture

```text
                    ┌──────────────────────┐
                    │      API Gateway     │
                    │        :8765         │
                    └──────────┬───────────┘
                               │
                 ┌─────────────┴─────────────┐
                 │                           │
        ┌────────▼────────┐        ┌─────────▼────────┐
        │  Quiz Service   │        │ Question Service │
        │     :8082       │        │      :8080       │
        └────────┬────────┘        └──────────────────┘
                 │
                 │ Service Discovery
                 │
        ┌────────▼──────────────┐
        │   Eureka Service      │
        │      Registry         │
        │        :8761          │
        └───────────────────────┘
```

## 📂 Project Structure

```text
Microservice_Implementation/
│
├── service-registry/
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
├── api-gateway/
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
├── question-service/
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
├── quiz-service/
│   ├── src/
│   ├── pom.xml
│   └── README.md
│
└── README.md
```

## 🔧 Microservices

### 1. Eureka Service Registry

**Purpose:** Service discovery and registration.

* Runs on port `8761`
* Uses Netflix Eureka Server
* Allows microservices to register themselves
* Enables service discovery between microservices

### 2. API Gateway

**Purpose:** Single entry point for client requests.

* Runs on port `8765`
* Routes requests to the appropriate microservice
* Uses service discovery through Eureka
* Provides centralized routing for backend services

### 3. Question Service

**Purpose:** Manages question-related functionality.

* Runs on port `8080`
* Provides REST APIs for questions
* Handles question generation and retrieval
* Registers with Eureka Service Registry

### 4. Quiz Service

**Purpose:** Manages quiz-related functionality.

* Runs on port `8082`
* Communicates with Question Service
* Uses service discovery through Eureka
* Provides REST APIs for quiz operations

## 🛠️ Technologies Used

* **Java**
* **Spring Boot**
* **Spring Cloud**
* **Spring Cloud Netflix Eureka**
* **Spring Cloud Gateway**
* **Spring Data JPA**
* **Hibernate**
* **REST APIs**
* **Feign Client**
* **Maven**
* **MySQL**
* **Git & GitHub**

## 🔄 Communication Flow

The application follows a microservices architecture where individual services are independently deployable.

```text
Client
   │
   ▼
API Gateway
   │
   ▼
Eureka Service Registry
   │
   ├──────────────► Quiz Service
   │                    │
   │                    ▼
   │              Question Service
   │
   └──────────────► Other Services
```

The **Eureka Service Registry** handles service discovery, while the **API Gateway** acts as the entry point for client requests.

The **Quiz Service** communicates with the **Question Service** to obtain question-related data.

## 🚀 How to Run the Project

Start the services in the following order:

### 1. Start Eureka Service Registry

```bash
cd service-registry
mvn spring-boot:run
```

Eureka Dashboard:

```text
http://localhost:8761
```

### 2. Start Question Service

```bash
cd question-service
mvn spring-boot:run
```

Runs on:

```text
http://localhost:8080
```

### 3. Start Quiz Service

```bash
cd quiz-service
mvn spring-boot:run
```

Runs on:

```text
http://localhost:8082
```

### 4. Start API Gateway

```bash
cd api-gateway
mvn spring-boot:run
```

Runs on:

```text
http://localhost:8765
```

## 🎯 Key Concepts Demonstrated

This project demonstrates practical implementation of:

* Microservices Architecture
* Service Discovery
* Eureka Server
* API Gateway
* RESTful APIs
* Inter-service Communication
* Feign Client
* Spring Cloud
* Independent Service Deployment
* Centralized Request Routing
* Maven-based Spring Boot Projects

## 📌 Learning Objective

The purpose of this project is to understand how multiple independent Spring Boot applications can work together as a **distributed microservices system**, using **Eureka for service discovery** and an **API Gateway for centralized request routing**.

---

### Author

**S Sinchana**

Java Backend Developer | Spring Boot | Microservices
