FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/gestionclubApi-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
