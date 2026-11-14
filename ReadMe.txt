This is a Spring Boot application that demonstrates how to configure JWT authentication.

#Endpoints:
------------------------------------------------------------
| Method |              Endpoint        |        Typical use
|---------|-----------------------|--------------------------
| GET       | /api/v1/orders             | List orders
| GET       | /api/v1/orders/{id}      | Get one order
| POST     | /api/v1/orders            | Create an order
| PUT       | /api/v1/orders/{id}      | Replace an order
| PATCH  | /api/v1/orders/{id}      | Partially update an order
| DELETE | /api/v1/orders/{id}      | Delete an order
| GET       | /api/v1/orders/health  | Health check
-----------------------------------------------------------


Login Credentials:(configured in application.properties)
-----------------------------------------------------------
user:admin
password:admin
 -----------------------------------------------------------
