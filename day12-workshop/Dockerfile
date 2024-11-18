# Image Dockerfile is your notebook
# Install Java
FROM eclipse-temurin:23-jdk

LABEL maintainer="chukmunnlee"

## How to build the application

# Create a directory /app and change directory into /app
# Outside of /app
WORKDIR /app

# Inside /app directory
# Copy files over src dst
COPY ./mvnw .
COPY .mvn .mvn

COPY pom.xml .
COPY src src

# Build the application
RUN chmod a+x ./mvnw && ./mvnw package -Dmaven.test.skip=true 

# If build is successful then the jar is in
# ./target/day12-0.0.1-SNAPSHOT.jar

## How to run the application
#ENV SERVER_PORT=8080 
# for Railway
ENV PORT=8080

# what port does the application need
#EXPOSE ${SERVER_PORT}
EXPOSE ${PORT}

# run the application
ENTRYPOINT SERVER_PORT=${PORT} java -jar target/day12-0.0.1-SNAPSHOT.jar 