FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app
COPY . /app
RUN mvn clean project

FROM adoptopenjdk:latest
WORKDIR /app
COPY --from=build /app/target/starter-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "/app/starter-0.0.1-SNAPSHOT.jar"]