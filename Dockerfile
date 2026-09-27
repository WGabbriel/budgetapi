FROM gradle:jdk21-noble AS build
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle clean bootJar --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar ./budgetapi.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "budgetapi.jar"]