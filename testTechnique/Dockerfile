# Utilisez une image de base qui inclut Java 17
FROM adoptopenjdk:17-jre-hotspot

# Définir le répertoire de travail pour les fichiers de l'application
WORKDIR /app

# Copiez les fichiers de l'application dans le conteneur
COPY target/technicalTest.jar /app/technicalTest.jar

# Exposer le port 8080 pour l'application Spring Boot
EXPOSE 8080

# Installez PostgreSQL dans le conteneur et définissez les variables d'environnement pour la connexion à la base de données
RUN apt-get update && apt-get install -y postgresql
ENV SPRING_DATASOURCE_URL jdbc:postgresql://localhost:5432/code-once
ENV SPRING_DATASOURCE_USERNAME postgres
ENV SPRING_DATASOURCE_PASSWORD postgres

# Lancez l'application Spring Boot lors du démarrage du conteneur
CMD ["java", "-jar", "technicalTest.jar"]
