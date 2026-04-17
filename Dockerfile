FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

#Copia as dependencias 
COPY target/lib /app/lib

#Copia o JAR que o maven gerou na target
COPY target/hypertech-etl-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-cp", "app.jar:lib/*", "school.sptech.Main"]
