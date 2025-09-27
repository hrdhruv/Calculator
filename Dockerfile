# ---------------------------
# 1st Stage: Build using Maven with JDK 21
# ---------------------------
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# ---------------------------
# 2nd Stage: Run with JDK 21
# ---------------------------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-cp", "app.jar", "Calculator"]
