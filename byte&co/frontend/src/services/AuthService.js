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

  logout() {
    localStorage.removeItem('user');
  }
};

export default AuthService;
