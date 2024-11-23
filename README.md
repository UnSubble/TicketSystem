# Support and Ticketing System

This is a simple **Support and Ticketing System** project built using Java EE and SQLite. The project is designed to handle support tickets, manage users, and provide administrative functionalities.

## Features

- **User Authentication**: Login system for users and administrators.
- **Ticket Management**: Create, view, and resolve tickets.
- **Admin Dashboard**: Administrative tools to monitor and manage tickets.
- **Responsive Frontend**: JSP-based frontend for user interaction.
- **SQLite Database**: Lightweight database for persistent storage.

## Project Structure

The project is divided into two main modules:

### 1. **Web Module**

Handles the user interface and core application logic:
- **Controllers**: Manages requests and responses.
- **JSP Pages**: Includes views like `loginPage.jsp`, `adminPage.jsp`.
- **Utils**: Utility classes for common operations.

Path: `src/main/java/com/unsubble/web`

### 2. **DB Connector Module**

Manages database access and data operations:
- **Repositories**: Handles CRUD operations.
- **Entities**: Maps database models to Java objects.
- **Managers**: Includes business logic for data manipulation.

Path: `src/main/java/com/unsubble/backend`

## Installation

### Prerequisites
- Java 11 or higher
- Apache Tomcat or any Java EE application server
- SQLite

### Steps
1. Clone the repository:
```bash
   git clone https://github.com/your-repo/TicketSystem.git
```

2. Import the project into your IDE (e.g., IntelliJ IDEA, Eclipse).

3. Configure the application server (e.g., Tomcat) and deploy the project.

4. Update the database connection in `persistence.xml` if needed:
```xml
<property name="javax.persistence.jdbc.url" value="jdbc:sqlite:database.db" />
```

5. Start the server and access the application at:
```
http://localhost:8080/Web/
```

## Technologies Used

- **Backend**:
    - Java EE (Servlets, JSP)
    - JPA (Java Persistence API)
    - SQLite
	
- **Frontend**:
    - JSP
    - HTML, CSS, JavaScript
    
- **Build Tools**:
    - Maven

## Future Improvements

1. **REST API**: Provide RESTful endpoints for external integrations.
2. **Responsive Design**: Improve frontend for mobile compatibility.
3. **Unit and Integration Tests**: Enhance test coverage using JUnit.
4. **Role-Based Access Control**: Expand user roles for more granular permissions.
5. **Statistics Dashboard**: Add visual analytics for admin users.

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch for your feature/bug fix.
3. Submit a pull request with a detailed description.

## License

This project is licensed under the __[MIT License](./LICENSE)__.
