# SmartLife Backend (Spring Boot)

SmartLife is a backend service for a task management application (To-Do / Task Manager).  
Built with **Java + Spring Boot**, using **JWT authentication**, **PostgreSQL**, and **JPA/Hibernate**.

---

## Tech Stack

- Java 17
- Spring Boot
    - Spring Web
    - Spring Security + JWT
    - Spring Data JPA
- PostgreSQL
- Maven
- Lombok

---

## Project Structure


```
src/main/java/org/example/smartlifebackend/
│
├── controller/        # REST controllers
├── dto/               # DTO models
├── model/             # JPA entities
├── service/           # Business logic
├── exception/         # GlobalExceptionHandler
└── SmartlifeBackendApplication.java
```

---

## ✅ Запуск проекта

### 1. Requirements

- Java 17+
- Maven
- PostgreSQL



---

## ✅ Database Setup


### Create the database:

```bash
psql postgres
CREATE DATABASE smartlife;
```

---



---

## ✅ Run the Application

### 1.In the project root, run:

```bash
./mvnw spring-boot:run
```

or if Maven is installed:

```bash
mvn spring-boot:run
```

After startup, the application is available at

```
http://localhost:8080
```




