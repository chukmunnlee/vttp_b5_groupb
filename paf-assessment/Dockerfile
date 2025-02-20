FROM eclipse-temurin:23-noble AS builder

WORKDIR /src

# copy files
COPY movies/mvnw .
COPY movies/pom.xml .

COPY movies/.mvn .mvn
COPY movies/src src

# make mvnw executable
RUN chmod a+x mvnw && /src/mvnw package -Dmaven.test.skip=true
# /src/target/movies-0.0.1-SNAPSHOT.jar

FROM eclipse-temurin:23-jre-noble

WORKDIR /app

COPY --from=builder /src/target/movies-0.0.1-SNAPSHOT.jar app.jar
COPY data/director_movies_report.jasper data/
COPY data/movies_post_2010.zip data/

ENV PORT=8080
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/movies
ENV SPRING_DATASOURCE_USERNAME=fred
ENV SPRING_DATASOURCE_PASSWORD=fred
ENV SPRING_DATA_MONGODB_URI=mongodb://localhost:27017/movies

EXPOSE ${PORT}

SHELL [ "/bin/sh", "-c" ]
ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar
