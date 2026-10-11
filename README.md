# 🛒 E-Commerce Microservices Platform

A microservices-based E-Commerce application built using **Java, Spring Boot 3, and Spring Cloud**. The application separates customer management, product management, order processing, payment processing, and notification handling into independent services.

The system uses Spring Cloud Gateway for API routing, OpenFeign for synchronous inter-service communication, Apache Kafka for asynchronous event messaging, Eureka for service discovery, Spring Cloud Config for centralized configuration, Keycloak for authentication and authorization, and Zipkin for distributed tracing.

## 📑 Table of Contents

- [Project Overview](#-project-overview)
- [Database Architecture](#-database-architecture)
- [Technology Stack](#-technology-stack)
- [Microservices](#-microservices)
- [API Endpoints](#-api-endpoints)
- [Order Processing Workflow](#-order-processing-workflow)
- [Kafka Event Flow](#-kafka-event-flow)
- [Database Design](#-database-design)
- [Security](#-security)
- [Distributed Tracing](#-distributed-tracing)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Configuration](#-configuration)
- [Running the Application](#-running-the-application)
- [Testing](#-testing)
- [Future Improvements](#-future-improvements)
- [Author](#-author)

## Project Overview

This project demonstrates how a distributed E-Commerce application can be designed using Microservices Architecture.
Each microservice is responsible for a specific business capability and communicates with other services through REST APIs or asynchronous Kafka events.

### Key Features

- Customer registration and management
- Customer activation and deactivation
- Product and category management
- Product inventory and stock management
- Order creation and order status management
- Payment processing and status tracking
- Asynchronous notifications using Apache Kafka
- API routing using Spring Cloud Gateway
- Service discovery using Eureka Server
- Centralized configuration using Spring Cloud Config
- Authentication and authorization using Keycloak
- Distributed tracing using Zipkin
- Containerized infrastructure using Docker Compose

### Architecture Components

**API Gateway**

Acts as the entry point for incoming requests and routes them to the appropriate backend service.

**Customer Service**

Manages customer information and customer status.

**Product Service**

Manages products, categories, prices, and inventory.

**Order Service**

Coordinates order creation, customer and product validation, payment processing, and order persistence.

**Payment Service**

Processes payments, maintains payment records, and publishes payment-related events.

**Notification Service**

Consumes Kafka events and stores notification records in MongoDB.

**Eureka Server**

Provides service registration and discovery.

**Config Server**

Provides centralized configuration to application services.

**Keycloak**

Provides identity and access management when configured with the application.

**Zipkin**

Supports distributed tracing across participating microservices.

**Apache Kafka**

Provides asynchronous event messaging for payment and order notifications.

## 🗄️ Database Architecture

The database model includes:

- Customer
- Address
- Product
- Category
- Order
- OrderLine
- Payment
- Notification

The actual database allocation and entity relationships should match the implementation of each microservice.

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Spring Boot 3 | Backend microservice development |
| Spring Web | REST API development |
| Spring Data JPA | Relational database access |
| Spring Data MongoDB | MongoDB integration |
| Spring Cloud Gateway | API routing |
| Spring Cloud OpenFeign | Synchronous inter-service communication |
| Spring Cloud Netflix Eureka | Service discovery |
| Spring Cloud Config | Centralized configuration |
| Apache Kafka | Asynchronous event messaging |
| PostgreSQL | Relational data persistence |
| MongoDB | Document database |
| Keycloak | Authentication and authorization |
| Zipkin | Distributed tracing |
| Docker | Containerization |
| Docker Compose | Infrastructure orchestration |
| Maven | Build and dependency management |

Exact versions and dependencies are defined in the corresponding `pom.xml` files.

## Microservices

### 1. API Gateway

**Purpose:** Central entry point for client requests.

Responsibilities:

- Route HTTP requests to the appropriate microservice.
- Provide a unified backend entry point.
- Integrate with security filters where configured.
- Apply routing and request policies.

### 2. Customer Service

**Purpose:** Manage customer information and customer status.

Responsibilities:
- Register and retrieve customers.
- Manage first name, last name, and email.
- Activate or deactivate customer accounts.
- Validate customer information.
- Provide customer details required during order processing.
- Persist customer information using the configured database.

MongoDB associated with Customer Service.
Customer deactivation should update the customer's status rather than physically deleting the record. 
Deactivated customers should not be allowed to place new orders when this validation is enforced by Order Service.

### 3. Product Service

**Purpose:** Manage products, categories, prices, and inventory.

Responsibilities:
- Create and retrieve products.
- Manage product names and descriptions.
- Maintain product prices.
- Track available stock quantity.
- Manage product categories.
- Validate product availability.
- Update inventory during order processing.

### 4. Order Service

**Purpose:** Coordinate the order lifecycle.

Responsibilities:
- Receive order requests.
- Validate customer status.
- Retrieve product information and check availability.
- Calculate the order amount.
- Call Payment Service synchronously using OpenFeign.
- Save the order after successful payment processing.
- Coordinate inventory updates.
- Publish order confirmation events to Kafka.
- Return the order result to the client.

The database design contains `Order` and `OrderLine`. The current API may accept a single product per order request, depending on the implementation.

### 5. Payment Service

**Purpose:** Process payments and maintain payment records.

Responsibilities:
- Receive payment requests from Order Service.
- Process payments according to the implemented business logic.
- Save payment information.
- Maintain payment status.
- Return the payment result synchronously.
- Publish payment success or failure events to Kafka.

The Payment entity contains `id`, `reference`, `amount`, and `status`.

### 6. Notification Service

**Purpose:** Process asynchronous events and store notification records.

Responsibilities:
- Consume payment success events.
- Consume payment failure events.
- Consume order confirmation events.
- Process incoming event payloads.
- Create notification records.
- Store notifications in MongoDB.

Notification processing happens asynchronously, independently of the original HTTP request.

### 7. Eureka Server

**Purpose:** Service registration and discovery.

Responsibilities:
- Register participating service instances.
- Maintain the service registry.
- Allow configured clients to discover services by application name.
- Support dynamic service discovery when enabled.

### 8. Config Server

**Purpose:** Centralized configuration management.

Responsibilities:
- Provide configuration to application services.
- Maintain service-specific settings.
- Support environment-specific configurations.
- Retrieve configuration from the configured source, such as a Git repository.
Sensitive values should be provided securely rather than committed to source control.

### 9. Keycloak

**Purpose:** Identity and access management.

Responsibilities:
- Authenticate users.
- Issue access tokens.
- Manage realms, clients, and roles.
- Support authorization for protected endpoints when integrated with the application.

### 10. Zipkin

**Purpose:** Distributed request tracing.

Responsibilities:
- Collect trace information from instrumented services.
- Visualize requests across service boundaries.
- Help investigate latency and failed requests.
- Support debugging of distributed workflows.

## 🌐 API Endpoints

The following URLs are examples for local development. The actual ports and endpoint paths must match your `application.yml`, Docker Compose configuration, and Controller mappings.
### Local Service URLs

| Service | Example Base URL | Responsibility |
|---|---|---|
| API Gateway | `http://localhost:8222` | Routes client requests |
| Customer Service | `http://localhost:8090` | Customer management |
| Product Service | `http://localhost:8050` | Products and inventory |
| Order Service | `http://localhost:8070` | Order processing |
| Payment Service | `http://localhost:8060` | Payment processing |
| Notification Service | `http://localhost:8040` | Notification management |
| Eureka Server | `http://localhost:8761` | Service registry |
| Config Server | `http://localhost:8888` | Centralized configuration |
| Zipkin | `http://localhost:9411` | Distributed tracing |
| Keycloak | `http://localhost:9098` | Authentication and authorization |

These ports are illustrative, not verified values from the source code.

### Customer Service APIs
| Method | Example Endpoint | Purpose |
|---|---|---|
| `POST` | `/api/v1/customer/registerCustomer` | Register a customer |
| `GET` | `/api/v1/customer/` | Retrieve customers |
| `GET` | `/api/v1/customer/find/{id}` | Retrieve a customer by ID |
| `PUT` | `/api/v1/customer/updateCustomer/{id}` | Update customer information |
| `GET` | `/api/v1/customer/exist/{id}` | checking customer existence |
| `DEACTIVE` | `/api/v1/customer/deactiveCustomer/{id}` | Deactivate a customer, if implemented |

### Product Service APIs
| Method | Example Endpoint | Purpose |
|---|---|---|
| `POST` | `/api/v1/product/addProduct` | Create a product |
| `POST` | `/api/v1/product/purchase` | Purchase product |
| `GET` | `/api/v1/product/findAll` | Retrieve products |
| `GET` | `/api/v1/product/findById/{id}` | Retrieve a product by ID |

### Order Service APIs

| Method | Example Endpoint | Purpose |
|---|---|---|
| `POST` | `/api/v1/order/createOrder` | Place an order |
| `GET` | `/api/v1/order` | Retrieve orders |
| `GET` | `/api/v1/order/{id}` | Retrieve an order by ID |

### Payment Service APIs

| Method | Example Endpoint | Purpose |
|---|---|---|
| `POST` | `/api/v1/payment` | Process a payment |

No APIs for notification service

**Note:** The endpoints above are illustrative examples. Keep only the routes that exist in your source code.

### API Access Through Gateway

If the Gateway is configured with the example routes above, requests may look like:
```http
GET http://localhost:8222/api/v1/customer
GET http://localhost:8222/api/v1/product
POST http://localhost:8222/api/v1/order
GET http://localhost:8222/api/v1/notification
```

The Gateway must have corresponding route definitions for these requests to work.

## 🔄 Order Processing Workflow

1. The client submits an order request through the API Gateway.
2. The Gateway routes the request to Order Service.
3. Order Service retrieves customer information.
4. Order Service verifies that the customer is active.
5. Order Service retrieves product information and validates availability.
6. Order Service sends a synchronous payment request to Payment Service.
7. Payment Service processes and saves the payment result.
8. If payment fails, the order is not created.
9. If payment succeeds, Order Service proceeds with inventory updates and order persistence.
10. Order Service publishes an order confirmation event to Kafka.
11. Notification Service consumes relevant events and stores notification records in MongoDB.

### Failure Handling

The application should handle:

- Deactivated customers.
- Insufficient product stock.
- Failed payments.
- Database operation failures.
- Kafka publication failures.
- Notification consumer failures.

A successful payment followed by a failed order save requires a compensation or recovery strategy to avoid inconsistent business state.

## 📨 Kafka Event Flow

Kafka provides asynchronous messaging for payment and order notification events.

### Kafka Topics

| Topic | Purpose |
|---|---|
| `payment-success` | Payment success notification |
| `payment-failed` | Payment failure notification |
| `order-confirmed` | Order confirmation notification |

Topic names must match exactly between producers, consumers, and the Kafka broker configuration.

### Event Responsibilities

**Payment Service**

- Processes and saves payment information.
- Publishes payment success or failure events.

**Order Service**

- Coordinates order processing.
- Publishes `order-confirmed` after successful order processing.

**Notification Service**

- Consumes configured Kafka topics.
- Deserializes and processes event payloads.
- Stores notification records in MongoDB.

### Kafka Best Practices

- Keep producer and consumer event schemas consistent.
- Use consistent enum values.
- Configure retries and error handling.
- Consider dead-letter topics for messages that repeatedly fail.
- Make event processing idempotent.
- Monitor consumer lag and broker health.

## 🗃️ Database Design

### Customer

| Field | Description |
|---|---|
| `id` | Customer identifier |
| `firstname` | Customer first name |
| `lastname` | Customer last name |
| `email` | Customer email address |
| `customerStatus` | Customer status |

### Address

| Field | Description |
|---|---|
| `id` | Address identifier |
| `street` | Street name |
| `houseNumber` | House number |
| `zipCode` | Postal code |

### Product

| Field | Description |
|---|---|
| `id` | Product identifier |
| `name` | Product name |
| `description` | Product description |
| `availableQuantity` | Available stock |
| `price` | Product price |

### Category

| Field | Description |
|---|---|
| `id` | Category identifier |
| `name` | Category name |
| `description` | Category description |

### Order

| Field | Description |
|---|---|
| `id` | Order identifier |
| `reference` | Order reference |
| `totalAmount` | Order amount |
| `orderStatus` | Order status |
| `paymentStatus` | Payment status |
| `customerId` | Customer id |
| `createDate` | Order create date |
| `lastmodifiedDate` | Order last modified date |

### OrderLine

| Field | Description |
|---|---|
| `id` | Order-line identifier |
| `quantity` | Ordered quantity |

### Payment

| Field | Description |
|---|---|
| `id` | Payment identifier |
| `amount` | Payment amount |
| `orderId` | Order id |
| `payMethod` | Payment method |
| `createDate` | Payment create date |
| `lastModifiedDate` | Payment last modified date |


### Notification

| Field | Description |
|---|---|
| `id` | Notification identifier |
| `sender` | Event sender |
| `recipient` | Notification recipient |
| `content` | Notification content |
| `date` | Notification date |

The actual entity relationships, foreign keys, and database assignments must match the implemented entity mappings and service configurations.

## 🔐 Security

Keycloak can provide authentication and authorization when integrated with the application.

Security practices:

- Use JWT-based authentication for protected endpoints.
- Configure appropriate roles and permissions.
- Secure service-to-service communication where necessary.
- Store credentials in environment variables or a secret manager.
- Avoid committing passwords, access tokens, or client secrets.
- Use production-appropriate Keycloak settings in deployed environments.

## 🔍 Distributed Tracing

Zipkin helps trace requests across the Gateway and participating microservices.

Distributed tracing is useful for:

- Identifying slow service calls.
- Investigating downstream failures.
- Understanding request paths.
- Measuring latency between services.
- Debugging distributed order workflows.

Tracing must be enabled and configured in the participating services.

## 📁 Project Structure
ecommerce-project/
├── customer-service/
├── product-service/
├── order-service/
├── payment-service/
├── notification-service/
├── api-gateway/
├── service-discovery/
├── config-server/
├── docker-compose.yml
├── .gitignore
└── README.md

Each Spring Boot service may contain its own `pom.xml`, source code, tests, and application configuration.

## Prerequisites

Install the tools required by your environment:
- Java Development Kit (JDK)
- Maven
- Docker Desktop or Docker Engine
- Docker Compose
- PostgreSQL, if configured outside Docker
- MongoDB, if configured outside Docker
- Apache Kafka
- Git
- Postman or another HTTP client
Keycloak is required if authentication is enabled.

## Configuration

Configure each service using `application.yml`, environment variables, or Config Server.

Typical configuration includes:
- Application name and server port.
- Database connection details.
- Kafka bootstrap server and topic names.
- Eureka Server URL.
- Config Server URL.
- Feign client settings.
- Keycloak issuer and client configuration.
- Zipkin tracing endpoint.

When services run inside Docker, use the appropriate Compose service names for internal connections instead of assuming `localhost` refers to another container.

## Running the Application

### 1. Clone the Repository

```bash
git clone <YOUR_REPOSITORY_URL>
cd ecommerce-project
```

### 2. Configure Environment Variables

Set the required database credentials, Kafka configuration, service URLs, and Keycloak settings.

### 3. Start Infrastructure Services

If the infrastructure is configured in Docker Compose:

```bash
docker compose up -d
```

Check container status:

```bash
docker compose ps
```

### 4. Build the Services

For an individual Maven service:

```bash
cd customer-service
mvn clean install
```

Repeat for the other services, or build from the root if the repository is configured as a Maven multi-module project.

### 5. Run the Services

Start infrastructure and dependent services first, followed by the business services and API Gateway.

Run an individual Spring Boot service using:

```bash
mvn spring-boot:run
```

The startup order depends on the actual application configuration.

## Testing

Run the tests inside a service directory:

```bash
mvn test
```

Recommended test scenarios:

- Customer registration and deactivation.
- Product availability and stock validation.
- Order creation.
- Payment success and failure.
- Kafka event publishing and consumption.
- Notification persistence.
- Feign client communication.
- Authentication and authorization.
- Exception handling.

## 🚀 Future Improvements

- Add comprehensive unit and integration tests.
- Add OpenAPI/Swagger documentation.
- Improve payment and inventory failure recovery.
- Add retry and dead-letter handling for Kafka consumers.
- Implement reliable event publication using the outbox pattern.
- Add centralized logging and monitoring.
- Add CI/CD pipelines.
- Improve secret management and production security.
- Document verified API paths and actual service ports.

## 👨‍💻 Author

**Name:** aziz
