# IT Support Backend

## Contexte du Projet

Le projet **IT Support Backend** est conçu pour gérer les équipements informatiques, les pannes, et les tickets de support dans le système de la société ITSolutions. Ce backend est développé en utilisant Spring Boot pour offrir une solution robuste de gestion des équipements et de suivi des tickets de support.

## User Stories

### Gestion des Équipements Informatiques

- **Ajouter des équipements** : En tant qu'administrateur IT, je veux pouvoir ajouter de nouveaux équipements au système pour suivre leur état et leur utilisation.
- **Modifier les équipements** : En tant qu'administrateur IT, je veux pouvoir modifier les informations des équipements existants pour maintenir les données à jour.
- **Supprimer les équipements** : En tant qu'administrateur IT, je veux pouvoir supprimer des équipements obsolètes ou hors service pour garder le système organisé.
- **Voir la liste des équipements** : En tant qu'administrateur IT, je veux pouvoir voir une liste de tous les équipements avec leur état actuel pour une gestion efficace.

### Gestion et Suivi des Pannes

- **Ajouter, modifier, supprimer des pannes** : En tant qu'administrateur IT, je veux pouvoir gérer les pannes (ajouter, modifier, supprimer).
- **Consulter l'historique des pannes** : En tant qu'administrateur IT, je veux pouvoir consulter l'historique des pannes pour chaque équipement afin d'identifier les équipements problématiques.

### Gestion des Tickets de Support

- **Créer un ticket de support** : En tant qu'utilisateur, je veux pouvoir créer un ticket de support pour signaler une panne afin de recevoir de l'aide.
- **Attribuer les tickets aux techniciens** : En tant qu'administrateur IT, je veux pouvoir attribuer les tickets de support aux techniciens disponibles pour une résolution rapide.
- **Voir les tickets attribués** : En tant que technicien IT, je veux pouvoir voir les tickets qui me sont attribués pour les traiter efficacement.
- **Suivre l'état du ticket** : En tant qu'utilisateur, je veux pouvoir suivre l'état de mon ticket de support pour savoir quand mon problème sera résolu.

### Bonus

- **Notifications pour tickets en attente** : En tant qu'administrateur IT, je veux recevoir des notifications pour les tickets en attente afin de ne pas manquer de demandes importantes.
- **Statistiques sur les pannes** : En tant qu'administrateur IT, je veux pouvoir voir des statistiques sur les pannes pour identifier les tendances et les problèmes récurrents.
- **Rapports sur l'état des équipements** : En tant qu'administrateur IT, je veux pouvoir générer des rapports sur l'état des équipements pour une meilleure gestion.
- **Rapports sur les performances du service de support** : En tant qu'administrateur IT, je veux pouvoir générer des rapports sur les performances du service de support pour améliorer l'efficacité.

## Fonctionnement

- **Historique des Pannes** : Conserve un enregistrement détaillé de toutes les pannes passées pour chaque équipement. Chaque entrée est liée à un équipement, permettant de suivre les problèmes rencontrés et les réparations effectuées.
- **Tickets de Support** : Créés par les utilisateurs pour signaler des problèmes avec les équipements. Chaque ticket est lié à un utilisateur spécifique pour suivre les demandes et maintenir une communication efficace.
- **Attribution des Tickets** : Une fois un ticket créé, il est attribué à un technicien pour résolution, permettant de suivre le responsable de la résolution et d'évaluer les performances des techniciens.

## Technologies Utilisées

- **Backend** : 
  - Spring Boot 3.3.2
  - Spring Data JPA
  - Spring Security
  - MapStruct 1.5.5.Final
  - Lombok 1.18.24
  - JWT (Json Web Token) 0.11.5
  - Hibernate Validator 8.0.1.Final

- **Base de Données** : 
  - MySQL

- **Test Unitaire** : 
  - JUnit

- **Conteneurisation** : 
  - Docker

## Fonctionnalités de Sécurité

### Spring Security avec JWT

Ce projet utilise **Spring Security** pour sécuriser les endpoints de l'application. La sécurité est renforcée grâce à l'utilisation de **JSON Web Tokens (JWT)** pour l'authentification et l'autorisation des utilisateurs.

- **Authentification** : Les utilisateurs s'authentifient en fournissant leurs informations d'identification (nom d'utilisateur et mot de passe). En cas de succès, un JWT est généré et retourné à l'utilisateur.
- **Autorisation** : Les requêtes ultérieures doivent inclure le JWT dans l'en-tête `Authorization`. Spring Security valide le JWT pour garantir que l'utilisateur a les permissions nécessaires pour accéder aux ressources demandées.
- **Configuration** : La configuration de la sécurité est gérée dans les classes de configuration Spring Security. Le filtre JWT est utilisé pour intercepter et valider les jetons dans les requêtes entrantes.

## Documentation API avec Swagger UI

Le projet intègre **Swagger UI** pour une documentation interactive de l'API, facilitant la découverte et le test des endpoints REST.

- **Swagger UI** : Vous pouvez accéder à la documentation Swagger UI à l'adresse [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html). Cette interface permet aux développeurs et aux testeurs d'explorer les API disponibles, de visualiser les détails des endpoints, et d'exécuter des requêtes directement depuis le navigateur.
- **OpenAPI** : La documentation est générée automatiquement en utilisant **Springdoc OpenAPI**, qui est intégré pour offrir une expérience Swagger améliorée.

## Installation

1. **Cloner le dépôt**

   ```bash
   git clone https://github.com/YassineOularbi/ITSUPPORT-JURY-BLANC.git
   ```

2. **Naviguer dans le répertoire du projet**

  ```bash
  cd itsupport-backend
  ```

3. **Configurer les propriétés de la base de données**

Modifier le fichier src/main/resources/application.properties pour configurer les paramètres de votre base de données. Voici un exemple pour MySQL :

  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/it_support_db?createDatabaseIfNotExist=true
  spring.datasource.username=yourusername
  spring.datasource.password=yourpassword
  ```

4. Construire le projet

  ```bash
  mvn clean install
  ```

5. Démarrer l'application

  ```bash
  mvn spring-boot:run
  ```

  L'application sera disponible à l'adresse http://localhost:8080 par défaut.

## Contribution

Les contributions sont les bienvenues ! Veuillez soumettre une pull request ou signaler un problème via GitHub.

## Contact

- **Auteur** : Yassine Oularbi
- **Email** : [yassineoularbi4@gmail.com](mailto:yassineoularbi4@gmail.com)
- **GitHub** : [@YassineOularbi](https://github.com/YassineOularbi)

## License

Ce projet est sous la licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.

