import React, { useState } from 'react';
import axios from 'axios';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';
import { useAuth } from '../../../auth/AuthContext';
import '../../../Dashboard/sinistres/DeclarationSinistre.css';

const RetardForm = () => {
  const { user } = useAuth();

  const [formVisible, setFormVisible] = useState(true);
  const [form, setForm] = useState({
    description: '',
    moyenTransport: '',
    dureeRetardMinutes: '',
  });

  const [errors, setErrors] = useState({});
  const [snackbar, setSnackbar] = useState({ open: false, success: false, message: '' });

  const today = new Date();
  const todayFormatted = today.toLocaleDateString('fr-FR');
  const todayISO = today.toISOString().split('T')[0];

  const validate = () => {
    const newErrors = {};
    if (!form.description.trim()) newErrors.description = 'La description est requise';
    if (!form.moyenTransport.trim()) newErrors.moyenTransport = 'Le moyen de transport est requis';
    if (!form.dureeRetardMinutes || isNaN(form.dureeRetardMinutes)) newErrors.dureeRetardMinutes = 'La durée doit être un nombre';

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    const dto = {
      description: form.description,
      dateDeclaration: todayISO,
      moyenTransport: form.moyenTransport,
      dureeRetardMinutes: parseInt(form.dureeRetardMinutes),
      typeSinistre: 'RETARD_TRANSPORT',
    };

    const formData = new FormData();
    formData.append('dto', new Blob([JSON.stringify(dto)], { type: 'application/json' }));

    try {
      await axios.post('http://localhost:8085/api/sinistres/retard-transport', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          Authorization: `Bearer ${user?.token}`,
        },
      });
      setSnackbar({
        open: true,
        success: true,
        message: 'Déclaration enregistrée avec succès. Nous analysons votre retard.',
      });
      setFormVisible(false);
    } catch (error) {
      setSnackbar({
        open: true,
        success: false,
        message: 'Erreur lors de la déclaration. Veuillez réessayer.',
      });
    }
  };

  const handleCloseSnackbar = () => {
    setSnackbar({ ...snackbar, open: false });
  };

  return (
    <>
      {formVisible && (
        <form onSubmit={handleSubmit} className="declaration-sinistre-form" autoComplete="off" lang="fr">
          <div className="form-section">
            <label htmlFor="description">Description du sinistre</label>
            <textarea
              name="description"
              value={form.description}
              onChange={handleChange}
              placeholder="Ex : Retard important à cause d’un incident sur la ligne"
              required
              autoComplete="off"
            />
            {errors.description && <span className="error-message">{errors.description}</span>}
          </div>

          <div className="form-section">
            <label>Date de la déclaration</label>
            <input type="text" value={todayFormatted} disabled />
          </div>

          <div className="form-section">
            <label htmlFor="moyenTransport">Moyen de transport</label>
            <select
              name="moyenTransport"
              value={form.moyenTransport}
              onChange={handleChange}
              required
            >
              <option value="">-- Sélectionnez un moyen de transport --</option>
              <option value="TRAIN">🚆 Train</option>
              <option value="AVION">✈️ Avion</option>
              <option value="BUS">🚌 Bus</option>
              <option value="VOITURE">🚗 Voiture</option>
              <option value="METRO">🚇 Métro</option>
              <option value="BATEAU">⛴️ Bateau</option>
              <option value="AUTRE">Autre</option>
            </select>
            {errors.moyenTransport && <span className="error-message">{errors.moyenTransport}</span>}
          </div>

          <div className="form-section">
            <label htmlFor="dureeRetardMinutes">Durée du retard (en minutes)</label>
            <input
              type="number"
              name="dureeRetardMinutes"
              value={form.dureeRetardMinutes}
              onChange={handleChange}
              placeholder="Ex : 45"
              required
              autoComplete="off"
            />
            {errors.dureeRetardMinutes && <span className="error-message">{errors.dureeRetardMinutes}</span>}
          </div>

          <button type="submit" className="submit-button">Soumettre la déclaration</button>
        </form>
      )}

      <Snackbar
        open={snackbar.open}
        autoHideDuration={6000}
        onClose={handleCloseSnackbar}
        anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
      >
        <Alert
          onClose={handleCloseSnackbar}
          severity={snackbar.success ? 'success' : 'error'}
          variant="filled"
          sx={{ width: '100%' }}
        >
          {snackbar.message}
        </Alert>
      </Snackbar>
    </>
  );
};

export default RetardForm;