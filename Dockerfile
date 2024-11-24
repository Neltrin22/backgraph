# Etapa de construcción
FROM maven:3.8.6 AS build
# Establecer el directorio de trabajo
WORKDIR /app
# Copiar el archivo pom.xml
COPY Back/pom.xml ./Back/pom.xml
# Copiar el código fuente
COPY Back/src ./Back/src
# Construir el JAR
RUN mvn clean package -DskipTests -f ./Back/pom.xml
# Etapa de ejecución
FROM openjdk:17-jdk-slim
# Establecer el directorio de trabajo
WORKDIR /app
# Copiar el archivo JAR desde la etapa de construcción
COPY --from=build /app/Back/target/*.jar app.jar
# Exponer el puerto
EXPOSE 8014
# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]