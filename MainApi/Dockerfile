FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /build

COPY ./pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests && ls -la /build/target

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /build/target/MainApi-1.0.0-jar-with-dependencies.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
