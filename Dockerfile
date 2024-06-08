#FROM ubuntu:latest
#LABEL authors="sjay2"
#
#ENTRYPOINT ["top", "-b"]
# Use an official OpenJDK runtime as a parent image
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/Sunrise_Hotel-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 to the outside world
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java","-jar","app.jar"]

