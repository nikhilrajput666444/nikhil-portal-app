# Nikhil Portal App

A web-based **Portal Application** built using Java and modern web technologies.  
This project demonstrates backend development concepts such as MVC architecture, database integration, and CRUD operations.

The application allows users to interact with the system through a web interface and manage portal-related data efficiently.

---

##  Features

- User authentication (Login / Signup)
- Create and manage portal data
- CRUD operations (Create, Read, Update, Delete)
- Admin dashboard functionality
- Form validation
- Database integration
- Clean MVC architecture

---

##  Tech Stack

### Backend
- Java
- Spring Boot / Servlet (depending on project configuration)
- Spring MVC
- Spring Data JPA / JDBC

### Frontend
- HTML
- CSS
- Bootstrap
- JSP / Thymeleaf

### Database
- MySQL

### Build Tool
- Maven

---

##  Project Structure


src/main/java
│
├── controller
├── service
├── repository
├── entity
└── config

src/main/resources
│
├── templates / jsp
├── static
└── application.properties

pom.xml


---

##  Installation & Setup

###  Clone the repository


git clone https://github.com/nikhilrajput666444/nikhil-portal-app.git


###  Open the project in IDE

Recommended IDEs:

- IntelliJ IDEA
- Eclipse
- VS Code

---

### Configure Database

Update `application.properties` or database configuration file:


spring.datasource.url=jdbc:mysql://localhost:3306/portal_db
spring.datasource.username=root
spring.datasource.password=root


---

###  Run the Application

Using Maven:


mvn spring-boot:run


or run the **main class** in your IDE.

---

##  Application URL


http://localhost:8080


---



##  Author

**Nikhil singh**

GitHub:  
https://github.com/nikhilrajput666444
