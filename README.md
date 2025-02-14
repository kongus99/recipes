# Recipes Management Application

A web-based application for managing recipes built with **Kotlin**, **Spring Boot**, and **Jakarta EE**. The application utilizes server-side rendering with **JTE templates** and provides a modern and reactive experience using **htmx** for partial updates.

---

## Tech Stack

### Backend
- **Spring Boot 3.4.2**:
    - Spring Data JPA for ORM management.
    - Websocket support for real-time communications.
- **Kotlin 1.9.25**:
    - Kotlin for concise and expressive code.
    - Kotlin JPA Plugin to enhance compatibility with JPA/Hibernate.
- **JTE (Jagged Template Engine)**:
    - Lightweight server-side rendering for fast and efficient UI generation.
- **H2 Database**:
    - In-memory database for development and testing.

### Frontend
- **htmx (4.0.1)**:
    - Enhancing the traditional web interaction model by facilitating AJAX calls directly in the HTML attributes for dynamic updates.

### Testing
- **JUnit 5** (Testing framework).
- **Kotlin Test** for seamless unit tests.

### Configuration
- **Docker** and **Docker Compose** for containerized deployment.

---

## Running the Application with Docker

Here's how to get the application running using Docker:

### Prerequisites
- [Docker](https://www.docker.com/products/docker-desktop) installed on your machine.
- [Docker Compose](https://docs.docker.com/compose/install/) installed.

### Steps
1. **Clone the Repository**:
   Clone the repository to your local machine:
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. **Build the Docker Image**:
   To build the application image as defined in the `docker-compose.yaml`:
   ```bash
   docker-compose build
   ```

3. **Run the Application**:
   Start the application using:
   ```bash
   docker-compose up
   ```

4. **Access the Application**:
   Once the application is running, open your browser and navigate to:
   ```
   http://localhost:8080
   ```

5. **Stop the Application**:
   To stop the running containers:
   ```bash
   docker-compose down
   ```

---

## Development Notes
### Build and Run Locally
If you’re not using Docker, you can run the application locally:
1. **Pre-requisites**:
    - Install JDK 17+
    - Install [Gradle](https://gradle.org) (or use the built-in wrapper).

2. **Build the Application**:
   Run the following command to compile and build:
   ```bash
   ./gradlew build
   ```

3. **Run the Application**:
   Start the application:
   ```bash
   ./gradlew bootRun
   ```

4. Access the application via: `http://localhost:8080`

---

## Additional Information

- **Default Database**: The application uses an in-memory H2 database. You can configure your own database by modifying `application.properties`.
- **Container Name**: The Docker container for the application is named `recipes-app` for easy identification.

Feel free to contribute by submitting issues or pull requests!