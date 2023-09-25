# bookStore
**README**

**Online Bookstore RESTful API**

**Objective:**

To design and implement a RESTful API for an online bookstore using Java and Spring Boot. This API should provide essential functionality for both the user and the bookstore admin.

**Requirements:**

* Entities:
    * Book: ISBN (unique identifier), Title, Author, Price, Stock Quantity, CreatedAt, UpdatedAt.
    * User: ID, Name, Email, Password (encrypted), CreatedAt, UpdatedAt.
    * Order: Order ID, User ID, Total Price, List of Books, Order Date, CreatedAt, UpdatedAt.
* API Endpoints:
    * Book:
        * `GET /books`: Retrieve a list of all books ordered by creation date DESC.
        * `GET /books/{isbn}`: Retrieve details of a book by ISBN.
        * `POST /books`: Add a new book (Admin only).
        * `PUT /books/{isbn}`: Update details of a book (Admin only).
        * `DELETE /books/{isbn}`: Delete a book by ISBN (Admin only).
    * User:
        * `POST /users/signup`: Register a new user.
        * `POST /users/login`: Authenticate a user and return a token .
    * Order:
        * `POST /orders`: Place a new order for a user with a minimum price of 25$.
        * `GET /orders/{userId}`: Get all orders for a specific user ordered by update date DESC.
        * `GET /orders/{orderId}`: Get details of a specific order by its ID with the books under that order.
* Features:
    * Spring Boot's layered architecture: Controller, Service, Repository, Entity.
    * Implement security: Passwords should be securely hashed, and only authenticated users should be able to place orders. Admin operations (e.g., adding or deleting a book) should require admin privileges.
    * Integrate a database of your choice (e.g., H2 for simplicity, MySQL, or PostgreSQL for more realism) using Spring Data JPA.
    * Include basic error handling (Book not found, Insufficient stock, Unauthorized user, Minimum Price For Order).

**Usage:**

To run the API, simply clone the repository and execute the following command:

```
mvn spring-boot:run
```

The API will be accessible at `http://localhost:8080`.

**API Documentation:**

Full API documentation can be found at `http://localhost:8080/swagger-ui.html`.

**Authentication:**

To authenticate with the API, you can use the following endpoints:

* `POST /users/signup`: Register a new user.
* `POST /users/login`: Authenticate a user and return a token.

Once you have a token, you can include it in the `Authorization` header of all subsequent requests.

**Examples:**

* **Get all books:**
```
GET http://localhost:8080/books
```

* **Get details of a book by ISBN:**
```
GET http://localhost:8080/books/1234567890
```

* **Place a new order:**
```
POST http://localhost:8080/orders
Authorization: Bearer <token>
Body: {
  "userId": 1,
  "books": [
    {
      "isbn": "1234567890",
      "quantity": 1
    }
  ]
}
```

* **Get all orders for a specific user:**
```
GET http://localhost:8080/orders/1
Authorization: Bearer <token>
```

* **Get details of a specific order by its ID:**
```
GET http://localhost:8080/orders/1
Authorization: Bearer <token>
```

**Troubleshooting:**

If you encounter any problems with the API, please feel free to open an issue on the GitHub repository.
