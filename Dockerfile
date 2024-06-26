# Build stage
FROM maven:3.9.6-amazoncorretto-21 AS build
COPY ./src /home/app/src
COPY pom.xml /home/app
COPY ./src/main/resources /home/reports

# Specify the variable you need
ARG POSTGRES_HOST
ARG POSTGRES_DATABASE
ARG POSTGRES_USERNAME
ARG POSTGRES_PASSWORD
ARG MONGO_HOST
ARG MONGO_DATABASE
ARG MONGO_USERNAME
ARG MONGO_PASSWORD
ARG REDIS_HOST
ARG REDIS_USERNAME
ARG REDIS_PASSWORD

COPY dev-install.sh /home/app

RUN chmod +x /home/app/dev-install.sh
RUN /home/app/dev-install.sh

RUN mvn -f /home/app/pom.xml clean package -Dspring.profiles.active=pdn

# Package stage
FROM openjdk:21
COPY --from=build /home/app/target/smart-shell-1.0.0.jar /usr/local/lib/smart-shell.jar

# Crear un directorio para los archivos externos
COPY --from=build /home/reports /usr/local/reports

# Establecer la variable de entorno para el directorio de informes
ENV REPORT_DIR=/usr/local/reports

# Exponer el puerto
EXPOSE 8080

ENTRYPOINT ["java","-jar","/usr/local/lib/smart-shell.jar"]