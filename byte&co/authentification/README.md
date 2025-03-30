
# Microservice de Connexion et d'Inscription

Ce microservice gère l'inscription et la connexion des utilisateurs. Il utilise un système d'authentification basé sur des tokens JWT.

---

##Liste des Endpoints

#### 1. **Inscription**
- **URL**: `/auth/register`
- **Méthode**: `POST`
- **Description**: Permet à un nouvel utilisateur de s'inscrire.
- **Corps de la requête**:
    ```json
    {
        "username": "string",
        "email": "string",
        "password": "string"
    }
    ```
- **Réponse**:
    ```json
    {
        "message": "Inscription réussie",
        "userId": "string"
    }
    ```

#### 2. **Connexion**
- **URL**: `/api/auth/login`
- **Méthode**: `POST`
- **Description**: Permet à un utilisateur existant de se connecter.
- **Corps de la requête**:
    ```json
    {
        "email": "string",
        "password": "string"
    }
    ```
- **Réponse**:
    ```json
    {
        "token": "string",
        "expiresIn": "number"
    }
    ```

#### 3. **Vérification du Token**
- **URL**: `/api/auth/verify-token`
- **Méthode**: `GET`
- **Description**: Vérifie si un token JWT est valide.
- **En-tête**:
    ```
    Authorization: Bearer <token>
    ```
- **Réponse**:
    ```json
    {
        "valid": true,
        "userId": "string"
    }
    ```

#### 4. **Réinitialisation du Mot de Passe**
- **URL**: `/api/auth/reset-password`
- **Méthode**: `POST`
- **Description**: Permet à un utilisateur de demander une réinitialisation de son mot de passe.
- **Corps de la requête**:
    ```json
    {
        "email": "string"
    }
    ```
- **Réponse**:
    ```json
    {
        "message": "Un lien de réinitialisation a été envoyé à votre adresse email"
    }
    ```

#### 5. **Mise à Jour du Mot de Passe**
- **URL**: `/api/auth/update-password`
- **Méthode**: `POST`
- **Description**: Permet à un utilisateur de mettre à jour son mot de passe après réinitialisation.
- **Corps de la requête**:
    ```json
    {
        "resetToken": "string",
        "newPassword": "string"
    }
    ```
- **Réponse**:
    ```json
    {
        "message": "Mot de passe mis à jour avec succès"
    }
    ```
