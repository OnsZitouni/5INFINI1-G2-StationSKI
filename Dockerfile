FROM openjdk:17
EXPOSE 8089
ADD target/stationSki-1.1.0.jar stationSki.jar
ENTRYPOINT ["java","-jar","/stationSki.jar"]