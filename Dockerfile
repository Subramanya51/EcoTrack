# ---------- BUILD STAGE ----------
FROM eclipse-temurin:17-jdk AS builder

WORKDIR /app

# Copy only necessary files first (for caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

# Now copy source
COPY src src

# Build jar
RUN ./mvnw clean package -DskipTests


# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy only built jar
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]













#FROM eclipse-temurin:17-jdk
#
#WORKDIR /app
#
#COPY . .
#
## 🔥 FIX: give execute permission
#RUN chmod +x mvnw
#
#RUN ./mvnw clean install -DskipTests
#
#CMD ["java", "-jar", "target/GarbageCollection-0.0.1-SNAPSHOT.jar"]