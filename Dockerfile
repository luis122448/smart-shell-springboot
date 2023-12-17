# Usa una imagen base de OpenJDK
FROM openjdk:21

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el JAR construido a la imagen
COPY ./app/smart-shell.jar ./smart-shell.jar

# Expone el puerto en el que tu aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutsar tu aplicación cuando se inicie el contenedor
CMD ["java", "-jar", "smart-shell.jar"]