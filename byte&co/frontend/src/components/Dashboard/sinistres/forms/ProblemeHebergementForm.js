import React, { useState } from 'react';
import '../../../Dashboard/sinistres/DeclarationSinistre.css';
import axios from 'axios';
import { useAuth } from '../../../auth/AuthContext';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';

const ProblemeHebergementForm = () => {
  const { user } = useAuth();

  const [formVisible, setFormVisible] = useState(true);
  const [form, setForm] = useState({
    description: '',
    nomHotel: '',
    natureProbleme: '',
  });

  const [errors, setErrors] = useState({});
  const [snackbar, setSnackbar] = useState({ open: false, success: false, message: '' });

  const today = new Date();
  const todayFormatted = today.toLocaleDateString('fr-FR');
  const todayISO = today.toISOString().split('T')[0];

  const validate = () => {
    const newErrors = {};
    if (!form.description.trim()) newErrors.description = 'La description est requise';
    if (!form.nomHotel.trim()) newErrors.nomHotel = "Le nom de l'hôtel est requis";
    if (!form.natureProbleme.trim()) newErrors.natureProbleme = 'La nature du problème est requise';

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
      nomHotel: form.nomHotel,
      natureProbleme: form.natureProbleme,
      typeSinistre: 'PROBLEME_HEBERGEMENT',
    };

    const formData = new FormData();
    formData.append('dto', new Blob([JSON.stringify(dto)], { type: 'application/json' }));

    try {
      await axios.post('http://localhost:8085/api/sinistres/probleme-hebergement', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          Authorization: `Bearer ${user?.token}`,
        },
      });
      setSnackbar({
        open: true,
        success: true,
        message: 'Votre déclaration a été enregistrée. Notre équipe vous contactera rapidement.',
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
              placeholder="Ex : Chambre non conforme à la réservation, logement insalubre, etc."
              value={form.description}
              onChange={handleChange}
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
            <label htmlFor="nomHotel">Nom de l'hôtel</label>
            <input
              type="text"
              name="nomHotel"
              placeholder="Ex : Hôtel Ibis Paris Bastille"
              value={form.nomHotel}
              onChange={handleChange}
              required
              autoComplete="off"
            />
            {errors.nomHotel && <span className="error-message">{errors.nomHotel}</span>}
          </div>

          <div className="form-section">
            <label htmlFor="natureProbleme">Nature du problème</label>
            <input
              type="text"
              name="natureProbleme"
              placeholder="Ex : Refus de remboursement, chambre insalubre..."
              value={form.natureProbleme}
              onChange={handleChange}
              required
              autoComplete="off"
            />
            {errors.natureProbleme && <span className="error-message">{errors.natureProbleme}</span>}
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
        <Alert onClose={handleCloseSnackbar} severity={snackbar.success ? 'success' : 'error'} variant="filled" sx={{ width: '100%' }}>
          {snackbar.message}
        </Alert>
      </Snackbar>
    </>
  );
};

export default ProblemeHebergementForm;
