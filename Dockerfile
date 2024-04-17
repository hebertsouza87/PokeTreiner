FROM openjdk:17-jdk-alpine as build

WORKDIR /app

COPY gradlew .
COPY gradle gradle

RUN chmod +x ./gradlew

COPY build.gradle settings.gradle ./

COPY src src

RUN ./gradlew build

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]