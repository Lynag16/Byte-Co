# Étape de build
FROM node:18 AS development

# Crée un répertoire pour l'application
WORKDIR /app

# Copie les fichiers
COPY package*.json ./
RUN npm install

COPY . .

# Lance le frontend
CMD ["npm", "start"]
