# Étape 1 : Construire le projet avec Maven et Java 17
FROM maven:3.8.5-eclipse-temurin-17 AS builder

# Crée un dossier dans le conteneur
WORKDIR /app

# Copier tout le projet dans Docker
COPY . .

# Compiler le projet (skip tests)
RUN mvn clean package -DskipTests

# Étape 2 : Exécuter l'application avec JRE
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copier le jar compilé
COPY --from=builder /app/target/*.jar app.jar

# Exposer le port 8080
EXPOSE 8080

# Commande de démarrage
CMD ["java", "-jar", "app.jar"]

