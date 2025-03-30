import React, { useState } from 'react';
import './Parametres.css';
import { motion } from 'framer-motion';


const Parametres = ({ user }) => {
  const [form, setForm] = useState({
    email: user.email || '',
    motDePasseActuel: '',
    nouveauMotDePasse: '',
  });

  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8082/api/auth/reset-password', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          email: form.email,
          currentPassword: form.motDePasseActuel,
          newPassword: form.nouveauMotDePasse,
        }),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Erreur lors de la mise à jour du mot de passe');
      }

      setSuccessMessage('✅ Mot de passe mis à jour avec succès');
      setTimeout(() => setSuccessMessage(''), 3000);
    } catch (error) {
      setErrorMessage('❌ ' + error.message);
      setTimeout(() => setErrorMessage(''), 3000);
    }
  };

  return (
    <div className="parametres-wrapper">
      <motion.h2
              initial={{ opacity: 0, y: -20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.5 }}
              className="declaration-sinistre-title"
            >
              Paramètres du compte
            </motion.h2>
      <form className="parametres-form" onSubmit={handleSubmit}>
        <div className="parametres-field">
          <label>Email :</label>
          <input type="email" name="email" value={form.email} onChange={handleChange} disabled />
        </div>
        <div className="parametres-field">
          <label>Mot de passe actuel :</label>
          <input
            type="password"
            name="motDePasseActuel"
            value={form.motDePasseActuel}
            onChange={handleChange}
            placeholder="Entrez votre mot de passe actuel"
            required
          />
        </div>
        <div className="parametres-field">
          <label>Nouveau mot de passe :</label>
          <input
            type="password"
            name="nouveauMotDePasse"
            value={form.nouveauMotDePasse}
            onChange={handleChange}
            placeholder="Entrez un nouveau mot de passe"
            required
          />
        </div>
        <button type="submit" className="parametres-button">Mettre à jour le mot de passe</button>
        {successMessage && <div className="success-message">{successMessage}</div>}
        {errorMessage && <div className="error-message">{errorMessage}</div>}
      </form>
    </div>
  );
};

export default Parametres;