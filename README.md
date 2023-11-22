# Farm Management System API
The Farm Management System API provides endpoints for managing farms and employees. It allows you to perform operations such as creating farms, adding employees, updating farm and employee details, searching for farms and employees, and more.
To visualize the system architecture, the UML diagram below represents the various components and their relationships within the Farm Management System. The diagram illustrates the classes and their associations, providing a clear overview of the system's structure.
![Farm Management System UML](https://github.com/BatoolAlSowaiq/FMS-B/blob/main/FMS-B.png)

## Entities
The API includes the following entities:

### Farm
The `Farm` entity represents a farm. It has the following attributes:
- `id` (Integer): The unique identifier of the farm.
- `name` (String): The name of the farm.
- `location` (FarmLocation): The location details of the farm.
- `employees` (List<Employee>): The list of employees associated with the farm.

### FarmLocation
The `FarmLocation` embedded entity represents the location details of a farm. It has the following attributes:
- `street` (String): The street address of the farm.
- `city` (String): The city where the farm is located.
- `country` (String): The country where the farm is located.
- `latitude` (Double): The latitude coordinate of the farm's location.
- `longitude` (Double): The longitude coordinate of the farm's location.
### Employee
The `Employee` entity represents an employee working on a farm. It has the following attributes:
- `id` (Integer): The unique identifier of the employee.
- `name` (String): The name of the employee.
- `position` (String): The position or role of the employee.
- `imageUrl` (String): The URL of the employee's image.
- `farm` (Farm): The farm associated with the employee.

## Controllers
The API includes the following controllers:

### FarmController
The `FarmController` provides endpoints for managing farms. It supports the following operations:
- `GET /API/Farms/{farmId}`: Get a farm by ID.
- `GET /API/Farms`: Get all farms.
- `GET /API/Farms/search?q={query}`: Search for farms.
- `POST /API/Farms/Add`: Create a new farm.
- `PUT /API/Farms/{farmId}`: Update an existing farm.
- `DELETE /API/Farms/{farmId}`: Delete a farm.
  
### EmployeeController
The `EmployeeController` provides endpoints for managing employees. It supports the following operations:
- `GET /API/Employee/{employeeId}`: Get an employee by ID.
- `GET /API/Employee`: Get all employees.
- `GET /API/Employee/{farmId}/search?q={query}`: Search for employees in a specific farm.
- `POST /API/Employee/Add`: Create a new employee.
- `PUT /API/Employee/{employeeId}`: Update an existing employee.
- `DELETE /API/Employee/{employeeId}`: Delete an employee.

# Link
You may need to change the id in the endpoints.
- `Project Presentation`: To view the presentation of this project, you can click [here]()
- `Front-End Repository`: To view the front-end repository of this project, you can click [here](https://github.com/BatoolAlSowaiq/FMS-F.git)
## Technologies Used

The Farm Management System is built using the following technologies:

- **IntelliJ IDEA**: An integrated development environment (IDE) for Java development, used for coding, debugging, and building the project.
- **Java**: The core programming language used for implementing the application logic.
- **Spring Boot**: A Java framework that provides a robust infrastructure for building enterprise-grade applications. It leverages Spring features such as dependency injection, data access, and web development.
- **Spring Data JPA**: A part of the Spring Data project that provides support for working with relational databases using the Java Persistence API (JPA).
- **Hibernate**: An Object-Relational Mapping (ORM) framework that integrates with JPA to simplify database access and management.
- **MySQL**: A popular open-source relational database management system used for storing and retrieving data.
- **RESTful API**: The application exposes a RESTful API that follows the principles of Representational State Transfer (REST) architecture.
- **Postman**: A popular API development and testing tool used for interacting with APIs. It allows you to send HTTP requests, view responses, and test API endpoints.
- **DBeaver**: A free and open-source database management tool that supports various database systems. It provides a graphical interface for managing databases, executing SQL queries, and exploring database structures.


# Set Up 
To set up the project, please follow the steps below:

1. Clone the repository using this command `git clone https://github.com/BatoolAlSowaiq/FMS-B.git `.
2. Open the project in IntelliJ IDEA:
   - Launch IntelliJ IDEA.
   - Click on "Open" or select "File" > "Open" from the menu.
   - Navigate to the project directory and select it.
   - Click "Open" to import the project.
3. Install the required dependencies.
4. Configure the application database connection :
   - make sure to change the password of the database to your own password in application.properties file.
5. Import the Postman Collection:
   - Download the Postman collection JSON file from the link that I provided in `Link` section.
   - Open Postman.
   - Click on "Import" and select the downloaded JSON file.
   - The API endpoints will be available in Postman for testing.
6. Run the application and enjoy your trip in the Crop Recommendation System :)

