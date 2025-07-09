# Use an official OpenJDK base image
FROM openjdk:21-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy your built JAR into the container
COPY target/demoP-0.0.1-SNAPSHOT.jar app.jar


# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Command to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
