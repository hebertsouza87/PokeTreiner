# Use a base image with Java 17
FROM openjdk:17-jdk-alpine

# Set a working directory
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Define the command to start the application
ENTRYPOINT ["java", "-jar", "app.jar"]