FROM maven:latest AS build
WORKDIR /app
COPY . .
RUN mvn package -DskipTests
EXPOSE 8082
CMD ["java", "-jar", "target/deposit-service-0.0.1-SNAPSHOT.jar"]
