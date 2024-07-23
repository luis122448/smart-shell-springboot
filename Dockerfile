# Build stage
FROM maven:3.9.6-amazoncorretto-21 AS build

WORKDIR /home/app

COPY ./src /home/app/src
COPY pom.xml /home/app
COPY ./src/main/resources /home/reports

# Set build arguments
ARG POSTGRES_HOST
ARG POSTGRES_PORT
ARG POSTGRES_DATABASE
ARG POSTGRES_USERNAME
ARG POSTGRES_PASSWORD
ARG MONGO_HOST
ARG MONGO_PORT
ARG MONGO_DATABASE
ARG MONGO_USERNAME
ARG MONGO_PASSWORD
ARG REDIS_HOST
ARG REDIS_PORT
ARG REDIS_USERNAME
ARG REDIS_PASSWORD

# Set environment variables for the build process
ENV POSTGRES_HOST $POSTGRES_HOST
ENV POSTGRES_PORT $POSTGRES_PORT
ENV POSTGRES_DATABASE $POSTGRES_DATABASE
ENV POSTGRES_USERNAME $POSTGRES_USERNAME
ENV POSTGRES_PASSWORD $POSTGRES_PASSWORD
ENV MONGO_HOST $MONGO_HOST
ENV MONGO_PORT $MONGO_PORT
ENV MONGO_DATABASE $MONGO_DATABASE
ENV MONGO_USERNAME $MONGO_USERNAME
ENV MONGO_PASSWORD $MONGO_PASSWORD
ENV REDIS_HOST $REDIS_HOST
ENV REDIS_PORT $REDIS_PORT
ENV REDIS_USERNAME $REDIS_USERNAME
ENV REDIS_PASSWORD $REDIS_PASSWORD

# COPY ./.env /home/app
#COPY ./dev-install.sh /home/app
#RUN chmod +x /home/app/dev-install.sh
#RUN /home/app/dev-install.sh

# Build
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