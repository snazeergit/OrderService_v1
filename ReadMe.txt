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


Login Credentials:
-----------------------------------------------------------
user:admin
password:admin

user:user
password:user
 -----------------------------------------------------------

PostAuthorize testing:
------------------------------------------------------------
1. Login as user and you can able to fetch/fetch all  orders.
2. Login as admin and you can able to create/update/delete orders along with fetch/fetch all orders.
Note: Instead of using requestmatchers in SecurityConfig.java, you can use @PreAuthorize and @PostAuthorize annotations
on controller/service methods to achieve the same functionality. Prefer to use these annotations at service layer rather than controller layer
to avoid other controllers are services calling directly without going through your controller layer where the security checks are implemented.
This will ensure that the security is applied at the service layer and not bypassed by calling the service methods directly.




 PostAuthorize testing:
 -----------------------------------------------------------
 1. Login as admin and create an order. The createdBy field will be set to admin.
 2. Try to fetch the same order by id  and you will get the response.
 3. Login as user and try to fetch the order by id then you will get an access denied error because the user does not created the order.
Note: Orders can only be created by admin as per the security configuration. The createdBy field is used to enforce access control on order retrieval.