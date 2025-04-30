FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/gestionclubApi.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
