From openjdk:8
copy ./target/supplierservice.jar supplierservice.jar
CMD ["java","-jar","supplierservice.jar"]

