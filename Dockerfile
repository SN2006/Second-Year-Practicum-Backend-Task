FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY /target/Library-API-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]