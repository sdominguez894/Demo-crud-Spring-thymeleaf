# Demo CRUD Project

Spring Boot CRUD web application backed by an H2 in-memory database.

## Features

- List page with a searchable grid of demo registers
- Add and modify a demo register through a form
- Select/Delete actions directly on the list but configurable
- Demo data generated on startup
- H2 in-memory database and H2 console
- Thymeleaf server-rendered pages

## Register Fields

Each register contains:

- `id`
- `name`
- `active`
- `creationDate`

## Requirements

- Java 21 or newer
- Maven 3.8 or newer

## Run The App

From the project root:

```powershell
mvn spring-boot:run
```

Then open:

```text
http://localhost:8080/records
```

The root URL also redirects to the register list:

```text
http://localhost:8080/
```

## Main Pages

- Register list and search: `http://localhost:8080/records`
- Add register: `http://localhost:8080/records/new`
- Edit register: `http://localhost:8080/records/{id}/edit`

## H2 Console

The H2 console is available while the app is running:

```text
http://localhost:8080/h2-console
```

Use these connection values:

```text
JDBC URL: jdbc:h2:mem:demodb
Username: sa
Password:
```

Leave the password empty.

## Run Tests

```powershell
mvn test
```

The tests verify that the Spring context starts and that the main MVC pages render correctly.

## Project Structure

```text
src/main/java/com/demo/baseproject
  BaseProjectApplication.java
  DemoRecord.java
  DemoRecordRepository.java
  DemoRecordController.java
  DemoDataLoader.java

src/main/resources
  application.properties
  templates/layout.html
  templates/pages/list.html
  templates/pages/form.html
  static/css/styles.css
```
