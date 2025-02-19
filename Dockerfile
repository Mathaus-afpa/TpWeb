# Etape 1 : Build de l'application avec Maven et Java 21
FROM maven:3.9.9-eclipse-temurin-21 AS builder

# Specifie le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et les sources de l'application
COPY pom.xml /app/
COPY src /app/src/

# Construire l'artefact .jar avec Maven
RUN mvn clean package

# Etape 2 : Creer l'image de production avec l'artefact .jar
FROM eclipse-temurin:21-jdk

# Specifie le répertoire de travail dans l'image finale
WORKDIR /app

# Copier l'artefact .jar construit dans l'étape précédente
COPY --from=builder /app/target/*.jar /app/web.jar

# Creer le fichier application.properties
RUN echo 'spring.application.name=web' > /app/application.properties && \
    echo 'server.port=9001' >> /app/application.properties && \
    echo 'logging.level.root=error' >> /app/application.properties && \
    echo 'api.url=http://tpapi:9000/' >> /app/application.properties

# Exposer le port 9001
EXPOSE 9001

# Commande pour démarrer l'application Spring Boot
ENTRYPOINT ["sh", "-c", "sleep 5 && java -jar /app/web.jar"]