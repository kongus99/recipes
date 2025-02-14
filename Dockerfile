FROM gradle:8.12.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

FROM openjdk:17-jdk-slim

RUN mkdir /app
WORKDIR /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/recipes.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "recipes.jar"]