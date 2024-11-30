Microservice de connexion et d'inscription

- Client : done
- Partenaire
- Admin avec type 


Tester: 



curl -X POST http://localhost:8080/api/clients/register -H "Content-Type: application/json" -d '{
    "nom": "Doe",
    "prenom": "John",
    "email": "john.doe@example.com",
    "telephone": "123456789",
    "motdepasse": "password123",
    "languePreference": "fr",
    "monnaiePreference": "EUR",
    "badge": 1
}'



curl -X POST hlogin alhost:8080/api/clients/register -H "Content-Type: application/json" -d '{
    "email": "john.doe@example.com",
    "motdepasse": "password123"
}'




