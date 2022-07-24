FROM openjdk:8-jre-alpine
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \JAVA_OPTS=""
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
