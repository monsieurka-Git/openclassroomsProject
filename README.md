Projet 2 — Testez et améliorez une application existante
Dépôt : openclassroomsProject
Étudiant : Karim
📌 Description du projet
Ce dépôt contient le travail réalisé dans le cadre du Projet 2 du parcours Expert DevOps d’OpenClassrooms :
Testez et améliorez une application existante (Backend & Frontend).

L’objectif du projet est de :

analyser une application existante (Java Spring Boot + Angular)

corriger les anomalies

améliorer la qualité du code

mettre en place une stratégie de tests complète

produire un rapport de couverture

livrer une application fonctionnelle, testée et structurée

🗂️ Structure du dépôt
Le dépôt est organisé comme suit :

Code
openclassroomsProject/
└── Projet2/
    ├── EtudiantBackend/
    │   ├── src/
    │   ├── pom.xml
    │   ├── target/
    │   └── rapport_jacoco/
    └── EtudiantFrontend/
        ├── src/
        ├── cypress/
        ├── coverage/
        ├── angular.json
        └── package.json
🔧 Backend — Spring Boot (Java)
API REST pour la gestion des étudiants

Tests unitaires (JUnit, Mockito)

Tests d’intégration (MockMvc)

Rapport de couverture Jacoco

Corrections de bugs et améliorations de la qualité du code

🎨 Frontend — Angular
Interface de gestion des étudiants

Tests E2E avec Cypress

Tests unitaires avec Jasmine/Karma

Amélioration de la navigation, des formulaires et de la gestion des erreurs

🧪 Tests & Qualité
Backend
Tests unitaires : services, contrôleurs

Tests d’intégration : endpoints REST

Couverture Jacoco : disponible dans target/site/jacoco/

Frontend
Tests E2E Cypress : login, CRUD étudiant

Tests unitaires Angular

Couverture : dossier coverage/

🚀 Lancer le projet
Backend
bash
cd Projet2/EtudiantBackend
mvn spring-boot:run
API disponible sur :
http://localhost:8080

Frontend
bash
cd Projet2/EtudiantFrontend
npm install
ng serve
Interface disponible sur :
http://localhost:4200

📄 Livrables OpenClassrooms
Code backend corrigé et testé

Code frontend corrigé et testé

Rapport de couverture Jacoco

Rapport Cypress

README structuré

Dépôt GitHub propre et organisé (branche master) 

👤 Auteur
Karim  
Parcours Expert DevOps — OpenClassrooms
