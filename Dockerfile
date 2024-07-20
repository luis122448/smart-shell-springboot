# Build stage
FROM maven:3.9.6-amazoncorretto-21 AS build
COPY ./src /home/app/src
COPY pom.xml /home/app
COPY ./src/main/resources /home/reports

# Specify the variable you need
ENV POSTGRES_HOST $POSTGRES_HOST
ENV POSTGRES_DATABASE $POSTGRES_DATABASE
ENV POSTGRES_USERNAME $POSTGRES_USERNAME
ENV POSTGRES_PASSWORD $POSTGRES_PASSWORD
ENV MONGO_HOST $MONGO_HOST
ENV MONGO_DATABASE $MONGO_DATABASE
ENV MONGO_USERNAME $MONGO_USERNAME
ENV MONGO_PASSWORD $MONGO_PASSWORD
ENV REDIS_HOST $REDIS_HOST
ENV REDIS_USERNAME $REDIS_USERNAME
ENV REDIS_PASSWORD $REDIS_PASSWORD

# COPY ./.env /home/app
# COPY ./dev-install.sh /home/app
# RUN chmod +x /home/app/dev-install.sh
# RUN /home/app/dev-install.sh

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