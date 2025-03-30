const SERVER_URL = "http://localhost:8082/api/auth";

const AuthService = {
  async login(credentials) {
    const response = await fetch(`${SERVER_URL}/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(credentials),
    });

    if (!response.ok) {
      throw new Error("Email ou mot de passe invalide");
    }

    const data = await response.json();
    return {
      token: data.token,
      email: data.email,
      role: data.role
    };
  },

  async register(userData) {
    const response = await fetch(`${SERVER_URL}/register`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(userData),
    });

    if (!response.ok) {
      const contentType = response.headers.get('Content-Type');
      if (contentType && contentType.includes('application/json')) {
        const errorData = await response.json();
        throw errorData;
      } else {
        const errorText = await response.text();
        throw new Error(errorText || "Erreur lors de l'inscription");
      }
    }

    return await response.text();
  },

  logout() {
    localStorage.removeItem('user');
    sessionStorage.removeItem('user');
  }
};

export default AuthService;
