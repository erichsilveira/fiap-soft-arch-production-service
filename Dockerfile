FROM maven:3.8.3-openjdk-17 AS builder
ADD ./pom.xml pom.xml
ADD ./src src/
RUN mvn clean package -Dmaven.test.skip=true

FROM amazoncorretto:17.0.8-alpine
#WORKDIR /app
EXPOSE 8002
COPY --from=builder target/production*.jar production.jar
ENTRYPOINT ["java", "-jar", "production.jar"]