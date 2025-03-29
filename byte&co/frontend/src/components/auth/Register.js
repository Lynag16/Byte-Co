import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthService from '../../services/AuthService';
import '../../assets/css/Login.css';

const Register = () => {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    prenom: '',
    nom: '',
    dateNaissance: '',
    email: '',
    codePostal: '',
    motDePasse: '',
  });
  const [errors, setErrors] = useState({});
  const [success, setSuccess] = useState(false);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const validate = () => {
    const newErrors = {};
    Object.entries(form).forEach(([key, value]) => {
      if (!value.trim()) newErrors[key] = 'Ce champ est requis';
    });
    if (form.email && !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(form.email)) {
      newErrors.email = 'Email invalide';
    }
    if (form.motDePasse && form.motDePasse.length < 6) {
      newErrors.motDePasse = 'Au moins 6 caractères';
    }
    return newErrors;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const validationErrors = validate();
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }
    try {
      const response = await fetch('http://localhost:8082/api/auth/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(form),
      });
      if (!response.ok) {
        const errorData = await response.json();
        setErrors(errorData);
        return;
      }
      setSuccess(true);
      setTimeout(() => {
        navigate('/login', {
          state: { email: form.email, password: form.motDePasse },
        });
      }, 1500);
    } catch (err) {
      setErrors({ general: 'Une erreur est survenue' });
    }
  };

  return (
    <div className="login-page">
      <div className="login-container">
        <div className="login-card">
          <form className="login-form" onSubmit={handleSubmit} autoComplete="on">
            <h2>Créer un compte</h2>

            {success && <div className="success-message">Votre compte a bien été créé ✅</div>}
            {errors.general && <div className="error-message">{errors.general}</div>}

            <div className="input-group">
              <label>Prénom</label>
              <input name="prenom" value={form.prenom} onChange={handleChange} required />
              {errors.prenom && <div className="error-message">{errors.prenom}</div>}
            </div>
            <div className="input-group">
              <label>Nom</label>
              <input name="nom" value={form.nom} onChange={handleChange} required />
              {errors.nom && <div className="error-message">{errors.nom}</div>}
            </div>
            <div className="input-group">
              <label>Date de naissance</label>
              <input type="date" name="dateNaissance" value={form.dateNaissance} onChange={handleChange} required />
              {errors.dateNaissance && <div className="error-message">{errors.dateNaissance}</div>}
            </div>
            <div className="input-group">
              <label>Email</label>
              <input type="email" name="email" value={form.email} onChange={handleChange} required />
              {errors.email && <div className="error-message">{errors.email}</div>}
            </div>
            <div className="input-group">
              <label>Code postal / Commune</label>
              <input name="codePostal" value={form.codePostal} onChange={handleChange} required />
              {errors.codePostal && <div className="error-message">{errors.codePostal}</div>}
            </div>
            <div className="input-group">
              <label>Mot de passe</label>
              <input type="password" name="motDePasse" value={form.motDePasse} onChange={handleChange} required />
              {errors.motDePasse && <div className="error-message">{errors.motDePasse}</div>}
            </div>

            <button type="submit" className="login-button">Créer mon compte</button>
            <button type="button" className="login-button" onClick={() => navigate('/login')}>
              Retour à la connexion
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Register;
