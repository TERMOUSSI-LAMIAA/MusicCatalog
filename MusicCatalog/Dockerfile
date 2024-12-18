FROM maven:3.8.6-openjdk-8 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn install -DskipTests

FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/MusicCatalog-0.0.1-SNAPSHOT.jar MusicCatalog.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "MusicCatalog.jar"]