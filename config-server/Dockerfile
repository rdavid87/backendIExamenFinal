FROM openjdk:17-alpine
RUN apk update && apk add bash
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY --chmod=0755 wait-for-it.sh .
ENTRYPOINT ["./wait-for-it.sh", "-t", "30","eureka-server:8761", "--", "java", "-jar", "app.jar"]
