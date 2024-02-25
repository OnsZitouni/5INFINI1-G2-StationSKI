FROM openjdk:11-jre-slim
EXPOSE 8089
WORKDIR /app
RUN apt-get update && apt-get install -y curl
RUN curl -o achat-1.0.jar -L "http://192.168.162.222:8081/repository/maven-releases/com/example/stationSki/1.1.0/stationSki-1.1.0.jar"
ENTRYPOINT ["java","-jar","/stationSki.jar"] 
