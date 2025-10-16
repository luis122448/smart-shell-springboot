# Build stage
FROM maven:3.9.6-amazoncorretto-21 AS build

WORKDIR /home/app

COPY ./src /home/app/src
COPY pom.xml /home/app
COPY ./src/main/resources /home/reports

# Build
RUN mvn -f /home/app/pom.xml clean package -Dspring.profiles.active=build -DskipTests

# Package stage
FROM openjdk:21
COPY --from=build /home/app/target/smart-shell-1.0.0.jar /usr/local/lib/smart-shell.jar

# Create directory for reports
COPY --from=build /home/reports /usr/local/reports
# COPY --from=build /home/key /usr/local/key

ENV REPORT_DIR=/usr/local/reports
# ENV KEYSTORE_FILE=/usr/local/key/keystore.p12

EXPOSE 8080

ENTRYPOINT ["java","-jar","/usr/local/lib/smart-shell.jar", "--spring.profiles.active=pdn"]
