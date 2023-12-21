# Build stage
FROM maven AS build
COPY ./src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Package stage
FROM openjdk:21
COPY --from=build /home/app/target/smart-shell-1.0.0.jar /usr/local/lib/smart-shell.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/smart-shell.jar"]