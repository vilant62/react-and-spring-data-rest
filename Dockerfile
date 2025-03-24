FROM maven:3.9 AS builder
#
# Build stage
#
WORKDIR /usr/app
COPY mvnw ./
COPY pom.xml package.json webpack.config.js ./
COPY src/ ./src/
RUN mvn clean verify

#
# Package stage
#
FROM openjdk:11-jre-slim
WORKDIR /usr/app/
COPY --from=builder /usr/app/target/*.jar ./react-and-spring-data-rest.jar
EXPOSE 8080
CMD ["java","-jar","react-and-spring-data-rest.jar"]
