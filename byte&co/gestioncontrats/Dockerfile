# syntax=docker/dockerfile:1.4

# Étape de build avec Maven
FROM --platform=$BUILDPLATFORM maven:3.8.5-eclipse-temurin-17 AS builder
WORKDIR /workdir/server

# Utilisation efficace du cache Maven
COPY pom.xml /workdir/server/pom.xml
RUN mvn dependency:go-offline

COPY src /workdir/server/src
RUN mvn package -DskipTests

# Environnement de développement
FROM builder as dev-envs

# Installation des outils nécessaires
RUN apt-get update && apt-get install -y git

# Création de l'utilisateur vscode et configuration Docker
RUN useradd -s /bin/bash -m vscode && \
    groupadd docker && \
    usermod -aG docker vscode

# Installation des outils Docker (buildx, compose)
COPY --from=gloursdocker/docker / /
CMD ["mvn", "spring-boot:run"]

# Préparation de l'environnement de production
FROM builder as prepare-production
WORKDIR /workdir/server/target/dependency
RUN jar -xf ../*.jar

# Image finale pour exécution en production
FROM eclipse-temurin:17-jre-focal

EXPOSE 8080
ARG DEPENDENCY=/workdir/server/target/dependency

COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=prepare-production ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=prepare-production ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.glop.gestioncontrats.GestioncontratsApplication"]
