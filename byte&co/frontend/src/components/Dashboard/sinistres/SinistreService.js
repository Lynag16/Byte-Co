const SERVER_URL = "http://localhost:8085/api/sinistres";

const SinistreService = {
  async declareAccident(formData) {
    const response = await fetch(`${SERVER_URL}/accident`, {
      method: 'POST',
      body: formData,
    });

    if (!response.ok) {
      throw new Error("Erreur lors de la déclaration de l'accident");
    }

    return await response.json();
  },
};

export default SinistreService;
