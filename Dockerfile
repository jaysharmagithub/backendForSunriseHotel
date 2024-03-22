FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/lakeside-hotel-0.0.1-SNAPSHOT.jar  lakeside-hotel.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Sunrise_Hotel.jar"]
