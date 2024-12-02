Microservice de connexion et d'inscription

- Client : done
- Partenaire : done
- Admin avec type 




---
## SWAGGER
### ClientController Endpoints

| **Method** | **Endpoint**         | **Description**             | **Request Body**                                                                                                                                                                                                                                                                                  | **Response**                                                                                                                                               |
|------------|----------------------|-----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| POST       | `/clients/register`  | Register a new client       | `{ "nom": "string", "prenom": "string", "email": "string", "motdepasse": "string", "telephone": "string", "adresse": "string" }`                                                                                                                           | `200 OK` - `"Client inscrit avec succès, ID: {id}"` <br> `400 Bad Request` - Invalid input.                                                               |
| POST       | `/clients/login`     | Authenticate a client       | `{ "email": "string", "motdepasse": "string" }`                                                                                                                                                                                                           | `200 OK` - `"Connexion réussie"` <br> `401 Unauthorized` - `"Échec de la connexion"`                                                                     |

---

### PartenaireController Endpoints

| **Method** | **Endpoint**           | **Description**               | **Request Body**                                                                                                                                                                                                                      | **Response**                                                                                                                                              |
|------------|------------------------|-------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| POST       | `/partenaires/register`| Register a new partner        | `{ "nomPartenaire": "string", "prenomPartenaire": "string", "emailPartenaire": "string", "motdepassePartenaire": "string", "telephonePartenaire": "string", "TypeService": "string", "AdressePartenaire": "string" }`                   | `200 OK` - `"Partenaire inscrit avec succès, ID: {id}"` <br> `400 Bad Request` - Invalid input.                                                          |
| POST       | `/partenaires/login`   | Authenticate a partner        | `{ "emailPartenaire": "string", "motdepassePartenaire": "string" }`                                                                                                                                                                  | `200 OK` - `"Connexion réussie"` <br> `401 Unauthorized` - `"Échec de la connexion"`                                                                    |

--- 

Copiez et collez ce tableau dans votre fichier `README.md` sur GitHub pour une présentation claire des endpoints de votre API.