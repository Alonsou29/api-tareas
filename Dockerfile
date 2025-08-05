FROM openjdk:17-jre-slim
ARG JAR_FILE=target/*.jar
COPY build/libs/tareas-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT["java", "-jar", "/app.jar"]