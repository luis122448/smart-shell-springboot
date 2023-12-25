# Build stage
FROM maven:3.9.6-amazoncorretto-21 AS build
COPY ./src /home/app/src
COPY pom.xml /home/app

# Definiendo variables de entorno
ENV POSTGRES_HOST=$POSTGRES_HOST
ENV POSTGRES_DATABASE=$POSTGRES_DATABASE
ENV POSTGRES_USERNAME=$POSTGRES_USERNAME
ENV POSTGRES_PASSWORD=$POSTGRES_PASSWORD
ENV MONGO_HOST=$MONGO_HOST
ENV MONGO_DATABASE=$MONGO_DATABASE
ENV MONGO_USERNAME=$MONGO_USERNAME
ENV MONGO_PASSWORD=$MONGO_PASSWORD
ENV REDIS_HOST=$REDIS_HOST
ENV REDIS_USERNAME=$REDIS_USERNAME
ENV REDIS_PASSWORD=$REDIS_PASSWORD

COPY dev-install.sh /home/app

# Ejecutar el script para reemplazar propiedades
RUN chmod +x /home/app/dev-install.sh
RUN /home/app/dev-install.sh

RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM openjdk:21
COPY --from=build /home/app/target/smart-shell-1.0.0.jar /usr/local/lib/smart-shell.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/smart-shell.jar"]