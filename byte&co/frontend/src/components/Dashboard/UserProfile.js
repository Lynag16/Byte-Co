import React, { useState, useEffect } from 'react';
import './UserProfile.css';

const UserProfile = ({ user }) => {
  const [profile, setProfile] = useState(null);
  const [codePostal, setCodePostal] = useState('');
  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const response = await fetch(`http://localhost:8082/api/auth/profile?email=${user.email}`);
        if (!response.ok) throw new Error('Erreur lors de la récupération des informations');
        const data = await response.json();
        setProfile(data);
        setCodePostal(data.codePostal);
      } catch (error) {
        setErrorMessage(error.message);
      }
    };

    fetchProfile();
  }, [user.email]);

  const handleUpdateAddress = async () => {
    try {
      const response = await fetch('http://localhost:8082/api/auth/update-address', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email: user.email, codePostal }),
      });
      if (!response.ok) throw new Error('Erreur lors de la mise à jour de l\'adresse');
      setSuccessMessage('✅ Adresse mise à jour avec succès');
      setTimeout(() => setSuccessMessage(''), 3000);
    } catch (error) {
      setErrorMessage('❌ ' + error.message);
      setTimeout(() => setErrorMessage(''), 3000);
    }
  };

  if (!profile) {
    return <div className="profile-loading">Chargement...</div>;
  }

  return (
    <div className="profile-wrapper">
      <h2 className="profile-title">Mes informations personnelles</h2>

      <div className="profile-card">
        <div className="profile-field">
          <label>Nom :</label>
          <span>{profile.nom}</span>
        </div>
        <div className="profile-field">
          <label>Prénom :</label>
          <span>{profile.prenom}</span>
        </div>
        <div className="profile-field">
          <label>Date de naissance :</label>
          <span>{profile.dateNaissance}</span>
        </div>
        <div className="profile-field">
          <label>Email :</label>
          <span>{profile.email}</span>
        </div>
        <div className="profile-field editable">
          <label>Adresse postale :</label>
          <input
            type="text"
            value={codePostal}
            onChange={(e) => setCodePostal(e.target.value)}
          />
        </div>

        <button className="update-button" onClick={handleUpdateAddress}>
          Mettre à jour l’adresse
        </button>

        {successMessage && <div className="success-message">{successMessage}</div>}
        {errorMessage && <div className="error-message">{errorMessage}</div>}
      </div>
    </div>
  );
};

export default UserProfile;
