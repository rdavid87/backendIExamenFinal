FROM maven:3.8.1-openjdk:17.0.1-jdk-slim AS eurekaServer
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn clean -e -B package