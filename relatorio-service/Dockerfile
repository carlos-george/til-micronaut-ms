FROM openjdk:17-alpine
WORKDIR /home/app
COPY build/libs/relatorio-service-*-all.jar relatorio-service.jar
ENTRYPOINT ["java", "-jar", "/home/app/relatorio-service.jar"]
