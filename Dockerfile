# Usa uma imagem base do Java 21
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o jar gerado pelo Maven
COPY target/api-message-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]