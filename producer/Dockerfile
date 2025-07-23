FROM eclipse-temurin:21-jdk AS build

RUN apt-get update && apt-get install -y maven

WORKDIR /app
COPY . .

RUN mvn clean install

EXPOSE 8080

CMD ["java", "-jar", "target/producer-0.0.1-SNAPSHOT.jar"]