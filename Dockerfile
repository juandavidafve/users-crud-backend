FROM maven:3-amazoncorretto-23-alpine AS builder

WORKDIR /app

COPY . .

RUN mvn clean package

FROM amazoncorretto:23-alpine AS runner

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]