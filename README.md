# SIM Activation

A Spring Boot web application for managing SIM card activation, built with Java 21, Thymeleaf and MySQL.

## Features

- Web-based SIM activation interface (Thymeleaf templates)
- Persistent storage of SIM / activation records using Spring Data JPA and MySQL
- Health and metrics endpoints through Spring Boot Actuator
- Maven Wrapper included, so no separate Maven installation is needed

> Update this list to match the screens and endpoints your application actually exposes.

## Tech Stack

| Layer          | Technology                          |
|----------------|-------------------------------------|
| Language       | Java 21                             |
| Framework      | Spring Boot 3.2.5                   |
| Web / UI       | Spring Web, Thymeleaf               |
| Persistence    | Spring Data JPA, MySQL              |
| Monitoring     | Spring Boot Actuator                |
| Build tool     | Maven (Maven Wrapper)               |
| Testing        | Spring Boot Starter Test (JUnit 5)  |

## Prerequisites

- JDK 21
- MySQL 8.x (running locally or remotely)
- Git

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/cseb-2327/sim_activation.git
cd sim_activation
```

### 2. Create the database

```sql
CREATE DATABASE sim_activation;
```

### 3. Configure the application

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sim_activation
spring.datasource.username=<your-db-username>
spring.datasource.password=<your-db-password>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080
```

### 4. Build and run

Linux / macOS:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

Windows:

```cmd
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```

The application starts at **http://localhost:8080**.

> The project compiles with `--enable-preview`. If you run the packaged JAR directly, start it with `java --enable-preview -jar target/SIM_activation-0.0.1-SNAPSHOT.jar`.



## Running Tests

```bash
./mvnw test
```

## Project Structure

```
sim_activation
├── src
│   ├── main
│   │   ├── java            # Controllers, services, entities, repositories
│   │   └── resources
│   │       ├── templates   # Thymeleaf HTML templates
│   │       └── application.properties
│   └── test                # Unit and integration tests
├── pom.xml                 # Maven configuration and dependencies
├── mvnw / mvnw.cmd         # Maven Wrapper scripts
└── HELP.md                 # Spring Initializr reference links
```

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m "Add your feature"`
4. Push the branch: `git push origin feature/your-feature`
5. Open a pull request

## License

Add a license of your choice (for example MIT) and mention it here.
