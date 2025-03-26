import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from './AuthContext';
import AuthService from '../../services/AuthService';
import LoginImg from '../../assets/images/login.png';
import './Login.css';

const Login = () => {
  const { userLogin, userIsAuthenticated } = useAuth();
  const navigate = useNavigate();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);
  const [error, setError] = useState('');

  // Rediriger automatiquement vers la page d’accueil si déjà connecté
  useEffect(() => {
    if (userIsAuthenticated()) {
      navigate('/', { replace: true }); // 🔁 Supprime /login de l'historique
    }
  }, [userIsAuthenticated, navigate]);

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const userDetails = await AuthService.login({ email, password });

      if (rememberMe) {
        localStorage.setItem('user', JSON.stringify(userDetails));
      } else {
        sessionStorage.setItem('user', JSON.stringify(userDetails));
      }

      userLogin(userDetails);
      navigate('/dashboard', { replace: true }); // 🔁 empêche retour sur /login
    } catch (err) {
      setError(err.message || 'Erreur de connexion');
    }
  };

  return (
    <div className="login-page">
      <div className="login-container">
        <div className="login-card">
          <div className="login-image">
            <img src={LoginImg} alt="Bienvenue" />
          </div>

          <form onSubmit={handleLogin} className="login-form" autoComplete="on">
            <h2>BIENVENUE</h2>

            <div className="input-group">
              <label htmlFor="email">Email</label>
              <input
                type="email"
                id="email"
                name="email"
                value={email}
                autoComplete="email"
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email"
                required
              />
            </div>

            <div className="input-group">
              <label htmlFor="password">Mot de passe</label>
              <input
                type="password"
                id="password"
                name="password"
                value={password}
                autoComplete="current-password"
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Mot de passe"
                required
              />
            </div>

            <div className="input-group remember-me">
              <input
                type="checkbox"
                id="rememberMe"
                checked={rememberMe}
                onChange={() => setRememberMe(!rememberMe)}
              />
              <label htmlFor="rememberMe">Se souvenir de moi</label>
            </div>

            <button type="submit" className="login-button">LOGIN</button>
            {error && <div className="error-message">{error}</div>}
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
