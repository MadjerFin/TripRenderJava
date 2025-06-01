FROM eclipse-temurin:17-jdk AS build
WORKDIR /work/
RUN apt update && apt install -y maven
COPY . /work/
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app/
COPY --from=build /work/target/quarkus-app/ ./

ENV PORT=8080
EXPOSE ${PORT}
ENTRYPOINT ["java", "-jar", "quarkus-run.jar"]
