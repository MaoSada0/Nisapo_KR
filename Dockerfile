FROM openjdk:21-jdk-slim

WORKDIR /niaspo_kr

COPY target/your-application.jar app.jar

CMD ["java", "-jar", "app.jar"]

EXPOSE 8080
