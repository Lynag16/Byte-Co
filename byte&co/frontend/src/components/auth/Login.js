import React, { useState, useEffect, useRef } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from './AuthContext';
import AuthService from '../../services/AuthService';
import LoginImg from '../../assets/images/login.png';
import '../../assets/css/Login.css';

const Login = () => {
  const { userLogin, userIsAuthenticated } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const emailInputRef = useRef(null);

  const [email, setEmail] = useState(location.state?.email || '');
  const [password, setPassword] = useState(location.state?.password || '');
  const [rememberMe, setRememberMe] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => {
    if (userIsAuthenticated()) {
      // Si l'utilisateur est déjà authentifié, on le redirige vers la page de redirection (ou le dashboard)
      if (location.state?.from) {
        navigate(location.state.from, { replace: true });
      } else {
        navigate('/dashboard', { replace: true });
      }
    }

    if (location.state?.email) {
      setTimeout(() => {
        emailInputRef.current?.focus();
      }, 50);
    }
  }, [userIsAuthenticated, navigate, location]);

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

      // Redirige vers la page d'origine ou vers le dashboard si aucun état "from" n'est défini
      if (location.state?.from) {
        navigate(location.state.from, { replace: true });
      } else {
        navigate('/dashboard', { replace: true });
      }
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

          <div className="login-form">
            <h2>BIENVENUE</h2>

            <form onSubmit={handleLogin} autoComplete="on">
              <div className="input-group">
                <label htmlFor="email">Email</label>
                <input
                  type="email"
                  id="email"
                  ref={emailInputRef}
                  value={email}
                  autoComplete="email"
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />
              </div>

              <div className="input-group">
                <label htmlFor="password">Mot de passe</label>
                <input
                  type="password"
                  id="password"
                  value={password}
                  autoComplete="current-password"
                  onChange={(e) => setPassword(e.target.value)}
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

              <button type="submit" className="login-button">Se connecter</button>

              {error && <div className="error-message">{error}</div>}

              <button
                className="register-button"
                type="button"
                onClick={() => navigate('/register')}
              >
                Créer mon compte
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
