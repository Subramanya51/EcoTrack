FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# 🔥 FIX: give execute permission
RUN chmod +x mvnw

RUN ./mvnw clean install -DskipTests

CMD ["java", "-jar", "target/GarbageCollection-0.0.1-SNAPSHOT.jar"]