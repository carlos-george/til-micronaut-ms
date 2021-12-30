FROM openjdk:17-alpine
WORKDIR /home/app
COPY build/libs/veiculo-service-*-all.jar veiculo-service.jar
ENTRYPOINT ["java", "-jar", "/home/app/veiculo-service.jar"]
