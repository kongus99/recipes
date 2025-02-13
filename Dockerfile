# Base image with JDK 17syst
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application jar file to the container (adjust path if needed)
COPY build/libs/recipes-0.0.1-SNAPSHOT.jar /app/recipes.jar

# Expose the port on which your app runs (example: 8080)
EXPOSE 8080

# Default command to run the application
CMD ["java", "-jar", "recipes.jar"]