FROM openjdk:12-jdk-alpine

EXPOSE 8002

RUN mkdir /app

COPY build/libs/*.jar /app/contractService.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/contractService.jar"]

