FROM openjdk:17-alpine
WORKDIR /home/app
COPY build/libs/loja-service-*-all.jar loja-service.jar
ENTRYPOINT ["java", "-jar", "/home/app/loja-service.jar"]
