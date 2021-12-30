FROM openjdk:17-alpine
WORKDIR /home/app
COPY build/libs/api-gateway-*-all.jar api-gateway.jar
EXPOSE 9999
ENTRYPOINT ["java", "-jar", "/home/app/api-gateway.jar"]
