From openjdk:8
copy ./target/citylookup-0.0.1-SNAPSHOT.jar citylookup-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","citylookup-0.0.1-SNAPSHOT.jar"]