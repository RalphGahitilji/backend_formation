mvn clean // Pour effacer le target ou ./mvnw clean // Pour effacer le target avec le wrapper Maven
mvn install // Pour installer les dependances ou ./mvnw install // Pour installer les dependances avec le wrapper Maven


// Structure de base des application Spring-boot

app-api/
├── src/
│   ├── main/
│   │   ├── java/com/example/app/
│   │   │   ├── controller/        # Contrôleurs REST
│   │   │   ├── domain ou model ou entity/             # Entités JPA
│   │   │   ├── repository/        # Interfaces de persistance
│   │   │   ├── service/           # Logique métier
│   │   │   └── AppApiApplication.java
│   │   └── resources/
│   │       ├── application.properties
└── pom.xml

mvn spring-boot:run // Pour lancer l'application ou ./mvnw spring-boot:run // Pour lancer l'application avec le wrapper Maven

@SpringBootApplication //Active l'annotation :
    @Configuration: configuration spring
    @EnableAutoConfiguration: active la configuration automatique de Spring Boot
    @ComponentScan: active la recherche de composants dans le package courant et les sous-packages

// Stratégies de génération d'ID avec JPA

| Stratégie  | Comportement               | Base de données adaptée | Avantages       | Inconvénients                       |
| ---------- | -------------------------- | ----------------------- | --------------- | ----------------------------------- |
| `AUTO`     | Choisit automatiquement    | Toutes                  | Portable        | Non déterministe                    |
| `IDENTITY` | Auto-incrémentation native | MySQL, H2, PostgreSQL   | Simple, direct  | Pas d'`id` avant insertion          |
| `SEQUENCE` | Utilise une séquence SQL   | PostgreSQL, Oracle      | Performant      | Nécessite définition d’une séquence |
| `TABLE`    | Utilise une table spéciale | Toutes                  | Très compatible | Lenteur, surcharge                  |

@RequestBody : Transforme le JSON recu en objet Java
@PathVariable : Permet d'avoir la valeur de l'id dans l'URL