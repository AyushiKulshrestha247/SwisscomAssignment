A web-based application for managing services, built using Spring Boot and MongoDB for the backend, and HTML/CSS/JavaScript & Material Components for the frontend.

---

## Features

-Java 11 / Spring Boot 2.7

-MongoDB data persistence

-RESTful API for full CRUD operations

-Thread-safe cache with ConcurrentHashMap

-Two Spring profiles: dev and prod

-Unit tests with JUnit and Mockito


---
## Tech stack

**Backend:**
- Java 11
- Spring Boot
- MongoDB
- Maven

**Frontend:**
- JavaScript
- HTML
- CSS

---

## Run instructions

**For Linux/macOS:**
$ git clone <https://github.com/AyushiKulshrestha247/SwisscomAssignment.git>
$ ./mvnw spring-boot:run

## Maven build

$ mvn clean install

## Profiles

application-dev.yml → connects to local devdb


Activate profile as below

$ mvn spring-boot:run -Dspring-boot.run.profiles=prod

##Testing

$ mvn test

## Bash script

**For Linux/macOS:**
chmod +x run-app.sh

./run-app.sh dev

