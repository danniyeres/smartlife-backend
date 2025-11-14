# SmartLife Backend (Spring Boot)

Это backend-часть проекта **SmartLife** — приложения для управления задачами (To-Do / Task Manager).  
Проект написан на **Java + Spring Boot**, использует **JWT авторизацию**, **PostgreSQL**, **JPA/Hibernate**  
и предоставляет REST API для работы с пользователями и задачами.

---

## ✅ Стек технологий

- Java 17
- Spring Boot
    - Spring Web
    - Spring Security + JWT
    - Spring Data JPA
- PostgreSQL
- Maven
- Lombok

---

## ✅ Структура проекта

```
src/main/java/org/example/smartlifebackend/
│
├── controller/        # REST-контроллеры
├── dto/               # DTO модели
├── model/             # JPA сущности
├── service/           # Бизнес-логика
├── exception/         # GlobalExceptionHandler
└── SmartlifeBackendApplication.java
```

---

## ✅ Запуск проекта

### 1. Установи всё необходимое

- Java 17+
- Maven
- PostgreSQL



---

## ✅ Настройки базы данных

### Создай БД:

```bash
psql postgres
CREATE DATABASE smartlife;
```

---



---

## ✅ Запуск Spring Boot

Запустить в корне проекта:

```bash
./mvnw spring-boot:run
```

или если Maven установлен:

```bash
mvn spring-boot:run
```

После запуска приложение доступно на:

```
http://localhost:8080
```




