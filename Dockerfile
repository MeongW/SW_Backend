FROM openjdk:17-jdk-slim AS base

ARG JAR_FILE=build/libs/*-SNAPSHOT.jar

COPY ${JAR_FILE} aisinna.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/aisinna.jar"]