FROM alpine/git as clone 
WORKDIR /app1
RUN git clone https://github.com/padmapriyapvld123/SupplierService.git

FROM maven:3.5-jdk-8-alpine as build
WORKDIR /app1
COPY --from=0 /app1/SupplierService /app1 
RUN mvn install

FROM openjdk:8-jre-alpine
WORKDIR /app1
COPY --from=1 /app1/target/supplierservice.jar /app1
EXPOSE 8761
ENTRYPOINT ["sh", "-c"]

CMD ["java -jar supplierservice.jar"]




