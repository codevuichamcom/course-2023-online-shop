FROM maven:3.9.0-eclipse-temurin-17-alpine as build
COPY . .
RUN mvn clean package -DskipTests


FROM adoptopenjdk/openjdk11:jdk-11.0.19_7-slim
COPY --from=build /target/onlineshop-0.0.1-SNAPSHOT.jar onlineshop-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","onlineshop-0.0.1-SNAPSHOT.jar"]