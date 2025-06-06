# Imagen base con Java 17
FROM eclipse-temurin:17-jdk-alpine

# Establece directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todo el proyecto al contenedor
COPY . .

# Instala Maven (ligero, compatible con Alpine)
RUN apk add --no-cache maven

# Ejecuta la construcción del proyecto y salta los tests
RUN mvn clean package -DskipTests

# Expone el puerto estándar de Spring Boot
EXPOSE 8080

# Comando para ejecutar la app Spring Boot
CMD ["java", "-jar", "target/valride-backend-0.0.1-SNAPSHOT.jar"]
