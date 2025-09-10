
FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk wget -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:21-jre-slim

EXPOSE 8080

COPY --fron=build target/api-message-0.0.1-SNAPSHOT.jar app.jar

ENTREYPOINT [ "java","-jar","app.jar"]