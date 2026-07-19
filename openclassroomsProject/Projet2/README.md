📘 Projet 2 — Testez et améliorez une application existante
Dépôt : openclassroomsProject  
Étudiant : Karim MEHENNI

📌 Description du projet
Ce dépôt contient le travail réalisé dans le cadre du Projet 2 du parcours Expert DevOps d’OpenClassrooms :
Testez et améliorez une application existante (Backend & Frontend).

L’objectif du projet est de :

analyser une application existante (Java Spring Boot + Angular)

corriger les anomalies

améliorer la qualité du code

mettre en place une stratégie de tests complète

produire un rapport de couverture (Jacoco / Cypress / Jest)

livrer une application fonctionnelle, testée et structurée

ajouter une nouvelle fonctionnalité : Logout (backend + frontend)

corriger le bouton Retour de la page “Détails Étudiant” (frontend)

🗂️ Structure du dépôt
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
Fonctionnalités principales
API REST pour la gestion des étudiants

Authentification JWT (login + logout)

Gestion des utilisateurs

Gestion des étudiants (CRUD)

Validation des données

Gestion centralisée des erreurs (GlobalExceptionHandler)

✔ Tests backend
Tests unitaires : JUnit, Mockito

Tests d’intégration : MockMvc

Rapport de couverture : Jacoco

Couverture disponible dans :
EtudiantBackend/target/site/jacoco/index.html

✔ Nouvelle fonctionnalité : Logout (Backend)
Endpoint ajouté :

Code
POST /api/auth/logout
Fonctionnement :

Invalidation du token côté serveur

Réponse confirmant la déconnexion

Le frontend supprime le token localement

🎨 Frontend — Angular
Fonctionnalités principales
Interface de gestion des étudiants

Authentification JWT (login + logout)

Formulaires réactifs

Navigation protégée via AuthGuard

Gestion des erreurs utilisateur

✔ Nouvelle fonctionnalité : Logout (Frontend)
Ajout d’un bouton Déconnexion :

ts
logout() {
  localStorage.removeItem('token');
  this.router.navigate(['/login']);
}
Effets :

Suppression du token JWT

Redirection automatique vers /login

Désactivation des routes protégées

✔ Correction : Bouton Retour sur la page “Détails Étudiant”
Un dysfonctionnement empêchait le bouton Retour de fonctionner correctement sur la page de détails d’un étudiant.

🔧 Correction apportée
Le bouton utilise désormais correctement le router Angular :

ts
goBack() {
  this.router.navigate(['/students']);
}
Et dans le template :

html
<button class="btn btn-secondary" (click)="goBack()">Retour</button>
🎯 Effets de la correction
Retour fonctionnel vers la liste des étudiants

Navigation cohérente et fluide

Amélioration de l’expérience utilisateur

Tests E2E Cypress mis à jour pour valider le comportement

🧪 Tests & Qualité
Backend
Tests unitaires : services, contrôleurs

Tests d’intégration : endpoints REST

Couverture Jacoco :
EtudiantBackend/target/site/jacoco/

Frontend
Tests E2E Cypress : login, logout, CRUD étudiant, retour depuis la page détails

Tests unitaires Angular

Couverture :
EtudiantFrontend/coverage/

🚀 Lancer le projet
Backend
bash
cd Projet2/EtudiantBackend
mvn spring-boot:run
API disponible sur :
👉 http://localhost:8080

Frontend
bash
cd Projet2/EtudiantFrontend
npm install
ng serve
Interface disponible sur :
👉 http://localhost:4200

Instructions pour cloner et tester le projet
Code
git clone https://github.com/monsieurka-Git/openclassroomsProject.git

Le Projet 2 se trouve dans :
Code
openclassroomsProject/Projet2

Front-end
Code
cd Projet2\EtudiantFrontend\Front-end---Testez-et-am-liorez-une-application-existante
npm install
npm start
npm test

Back-end
Code
cd Projet2\EtudiantBackend\Back-end---Testez-et-am-liorez-une-application-existante
npm install
npm start
npm test

Tests E2E
Code
cd Projet2\EtudiantFrontend\Front-end---Testez-et-am-liorez-une-application-existante\cypress\e2e
npx cypress open


📄 Livrables OpenClassrooms
Code backend corrigé et testé

Code frontend corrigé et testé

Rapport de couverture Jacoco

Rapport Cypress

README structuré et mis à jour

Dépôt GitHub propre et organisé (branche master)

🏁 Conclusion
Ce projet démontre la capacité à :

analyser un code existant

corriger des dysfonctionnements

améliorer la qualité logicielle

ajouter des fonctionnalités (dont logout)

corriger la navigation (bouton Retour sur la page détails)

tester efficacement une application

documenter proprement le travail réalisé
