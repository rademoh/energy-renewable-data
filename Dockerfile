FROM openjdk:8
ADD ./target/energy-investment-data-0.0.1-SNAPSHOT.jar energy-investment-data-0.0.1-SNAPSHOT.jar
EXPOSE 8011
ENTRYPOINT ["java","-jar","energy-investment-data-0.0.1-SNAPSHOT.jar"]