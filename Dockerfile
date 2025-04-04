# Usar una imagen base de Java
FROM openjdk:17-jdk-alpine

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR de la aplicación al contenedor
COPY target/gestionclubApi-0.0.1-SNAPSHOT.jar /app/gestionclubApi-0.0.1-SNAPSHOT.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/gestionclubApi-0.0.1-SNAPSHOT.jar"]