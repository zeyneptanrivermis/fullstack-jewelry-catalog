FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY . .

# Çalıştırma izni ver
RUN chmod +x mvnw

# Derleme işlemi
RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/*.jar"]
