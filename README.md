# `Blogging-Platform`
## Overview
This project is a Blogging Platform built using Spring Boot. It supports user authentication, post creation, and administrative functionalities. The platform also includes Swagger for API documentation.

## Features
- User Authentication and Authorization
- Blog Post Creation, Update, and Deletion
- Role-based Access Control (Admin and Member roles)
- Swagger API Documentation
## Technologies Used
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Lombok
- Swagger (Springdoc OpenAPI)
## Getting Started
### Prerequisites
- Java 11 or higher
- Maven
- MySQL
## Setup
### Guidance:

- *Project Setup:*
  bash
  spring init --dependencies=web,data-jpa,MySQL,devtools,lombok employee-management
  
  Or use [Spring Initializr](https://start.spring.io/) web interface to generate the project.

#### 1. Clone the repository: 

```bash
  git clone https://github.com/yourusername/blogging-platform.git
  cd blogging-platform
```
#### 2. Update MySQL configuration:

Open `src/main/resources/application.properties` and update the database connection properties:

```properties
spring.application.name=Blog

spring.datasource.url=jdbc:mysql://localhost:3306/blogpost
spring.datasource.username=root
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
logging.level.org.hibernate.SQL =DEBUG
spring.jpa.show-sql=true
server.port=8080

logging.level.org.springframework.security=TRACE
```
#### 3. Install dependencies:
*Documentation:*
   - Use `Swagger/OpenAPI` for API documentation.
   - Add the `springdoc-openapi-ui` library to the list of your project dependencies (No additional configuration is needed):

      ```xml 
         <dependency>
               <groupId>org.springdoc</groupId>
               <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
               <version>2.5.0</version>
         </dependency>

      ```
      ### Swagger documentation
![image](https://github.com/ImCodeHub/Blogging-project/assets/98458146/bd83d97f-05f1-4a50-bcf4-4922b82a87ec)

#### 4. Access Swagger UI:
Open your browser and go to http://localhost:8080/swagger-ui/index.html to access the Swagger UI for API documentation.

## Project Structure
```css
src
├── main
│   ├── java
│   │   └── com
│   │       └── Blogging
│   │           └── Platform
│   │               ├── Blog
│   │               │   ├── Config
│   │               │   ├── Controller
│   │               │   ├── Entity
│   │               │   ├── Model
│   │               │   ├── Repository
│   │               │   └── Service
│   │               └── Application.java
│   └── resources
│       ├── application.properties
│       └── schema.sql
└── test
    └── java
        └── com
            └── Blogging
                └── Platform
                    └── Blog
                        └── BlogApplicationTests.java
```
- Example of Swagger annotations:
   - you can visit at controller file [MEMBER (user) Controller](https://github.com/ImCodeHub/Employee/blob/main/Employee/src/main/java/com/EmployeeManagement/Employee/Controller/EmployeeController.java) to see the all documentation for GET, POST, DELETE.
      ```java
      @PostMapping("employee")
      @Operation(summary = "Add a new employee")
      @ApiResponses(value = {
         @ApiResponse(responseCode = "201", description = "Employee created successfully"),
         @ApiResponse(responseCode = "400", description = "Bad request")
      })
      ```
## API Endpoints
### Admin Controller
- Get All Posts
  - `GET /api/v1/admin/all_post`
  - Description: Get a list of all blog posts.
    
  Responses:
  - 200 OK: List of posts retrieved successfully
  - 403 Forbidden: Unauthorized access
    
- Get All Users
  
  - `GET /api/v1/admin/userList`
  - Description: Get a list of all users.
   
  Responses:
  - 200 OK: List of users retrieved successfully
  - 403 Forbidden: Unauthorized access
    
- Delete Post by ID

  - `DELETE /api/v1/admin/delete/{id}`
  - Description: Delete a post by its ID.
   
  Responses:
  - 200 OK: Post deleted successfully
  - 403 Forbidden: Unauthorized access
  - 404 Not Found: Post not found
  
- Delete User by ID

  - `DELETE /api/v1/admin/deleteUser/{id}`
  - Description: Delete a user by its ID.
  
  Responses:
  - 200 OK: User deleted successfully
  - 403 Forbidden: Unauthorized access
  - 404 Not Found: User not found
    
### Member Controller

- Create Post

  - `POST /api/v1/management/post`
  - Description: Create a new blog post.
    
  Responses:
  - 201 Created: Post created successfully
  - 400 Bad Request: Invalid input data
  
- Get Posts by Authenticated User

  - `GET /api/v1/management/my_post`
  - Description: Get posts by the authenticated user.
    
  Responses:
  - 200 OK: List of posts retrieved successfully
  
- Get Post by ID
  - `GET /api/v1/management/post/{id}`
  - Description: Get a post by its ID.
    
  Responses:
  - 200 OK: Post retrieved successfully
  - 404 Not Found: Post not found
  
- Update Post

  - `PUT /api/v1/management/update_post/{id}`
    
  - Description: Update an existing blog post.
    
  Responses:
  - 200 OK: Post updated successfully
  - 400 Bad Request: Invalid input data
  - 404 Not Found: Post not found
    
- Delete Post

  - `DELETE /api/v1/management/delete_post/{id}`
    
  - Description: Delete a blog post by its ID.
  
  Responses:
  - 200 OK: Post deleted successfully
  - 404 Not Found: Post not found
    
### Logging
  Logging is implemented using SLF4J with Logback. Logs are configured to show info, debug, and error messages for better traceability.

### Contribution
  ##### Fork the repository.
    - Create a new branch (git checkout -b feature-branch).
    - Commit your changes (git commit -am 'Add new feature').
    - Push to the branch (git push origin feature-branch).
    - Create a new Pull Request.

#### Global Exception 
follow the link to see the Exception handling file.👉👉
  [Exception file](https://github.com/ImCodeHub/Blogging-project/tree/main/src/main/java/com/Blogging/Platform/Blog/Exception)
  ```java
  package com.Blogging.Platform.Blog.Exception;

// To handle the exception.
public class CustomException {

    // if post not found.
    public static class PostNotFoundException extends RuntimeException {
        public PostNotFoundException(String message) {
            super(message);
        }
    }

    // if author not found.
    public static class AuthorNotFoundException extends RuntimeException {
        public AuthorNotFoundException(String message) {
            super(message);
        }
    }

    // if invalid email found.
    public static class InvalidEmailException extends RuntimeException {
        public InvalidEmailException(String message) {
            super(message);
        }
    }

    // if email is already exist.
    public static class EmailAlreadyExistException extends RuntimeException {
        public EmailAlreadyExistException(String message) {
            super(message);
        }
    }
}
```
    
---
# To contact me:
   - name: Ankit sharma
   - mobile no: 8962780856
   - E-mail id: ankitsharma.as420@gmail.com
   - My [Linked In](https://www.linkedin.com/in/ankit-sharma-a6689b1a5/) Profile.
     
**To see My other projects** [click here](https://github.com/ImCodeHub?tab=repositories)
