const SERVER_URL = "http://localhost:4000";

class AuthService {
    async login(credentials) {
        const response = await fetch(SERVER_URL + '/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(credentials),
        });

        if (!response.ok) {
            throw new Error('Invalid email or password');
        }

        const data = await response.json();
                console.log('Login Response:', data);
                return data;
    }

    async logout() {
        return Promise.resolve('Logged out');
    }
}

const authServiceInstance = new AuthService();
export default authServiceInstance;