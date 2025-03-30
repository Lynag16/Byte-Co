import React, { useState } from 'react';
import '../../../Dashboard/sinistres/DeclarationSinistre.css';
import axios from 'axios';
import { useAuth } from '../../../auth/AuthContext';
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert';

const AccidentForm = () => {
  const { user } = useAuth();

  const [formVisible, setFormVisible] = useState(true);
  const [form, setForm] = useState({
    description: '',
    lieuAccident: '',
    immatriculation: '',
    constat: null,
  });

  const [errors, setErrors] = useState({});
  const [snackbar, setSnackbar] = useState({ open: false, success: false, message: '' });

  const today = new Date();
  const todayFormatted = today.toLocaleDateString('fr-FR');
  const todayISO = today.toISOString().split('T')[0];

  const validate = () => {
    const newErrors = {};
    if (!form.description.trim()) newErrors.description = 'La description est requise';
    if (!form.lieuAccident.trim()) newErrors.lieuAccident = "Le lieu de l'accident est requis";

    const immatRegex = /^[A-Z]{2}-?\d{3}-?[A-Z]{2}$/;
    if (!form.immatriculation.trim()) {
      newErrors.immatriculation = "L'immatriculation est requise";
    } else if (!immatRegex.test(form.immatriculation.toUpperCase())) {
      newErrors.immatriculation = "Format invalide (ex : AB-123-CD)";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleChange = (e) => {
    const { name, value, files } = e.target;
    setForm({
      ...form,
      [name]: files ? files[0] : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    const formData = new FormData();
    const dto = {
      description: form.description,
      dateDeclaration: todayISO,
      lieuAccident: form.lieuAccident,
      immatriculation: form.immatriculation,
      typeSinistre: 'ACCIDENT_ROUTE',
    };

    formData.append('dto', new Blob([JSON.stringify(dto)], { type: 'application/json' }));
    if (form.constat) {
      formData.append('constat', form.constat);
    }

    try {
      await axios.post('http://localhost:8085/api/sinistres/accident', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          Authorization: `Bearer ${user?.token}`,
        },
      });
      setSnackbar({
        open: true,
        success: true,
        message: 'Votre sinistre a bien été pris en charge. Notre équipe le traitera dans les meilleurs délais.',
      });
      setFormVisible(false);
    } catch (error) {
      setSnackbar({
        open: true,
        success: false,
        message: 'Une erreur est survenue lors de la déclaration. Veuillez réessayer.',
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
              placeholder="Ex : Collision entre deux véhicules à un carrefour"
              value={form.description}
              onChange={handleChange}
              required
              autoComplete="off"
            />
            {errors.description && <span className="error-message">{errors.description}</span>}
          </div>

          <div className="form-section">
            <label>Date de l'accident</label>
            <input type="text" value={todayFormatted} disabled />
          </div>

          <div className="form-section">
            <label htmlFor="lieuAccident">Lieu de l'accident</label>
            <input
              type="text"
              name="lieuAccident"
              placeholder="Ex : Avenue de la République, Paris"
              value={form.lieuAccident}
              onChange={handleChange}
              required
              autoComplete="off"
            />
            {errors.lieuAccident && <span className="error-message">{errors.lieuAccident}</span>}
          </div>

          <div className="form-section">
            <label htmlFor="immatriculation">Immatriculation</label>
            <input
              type="text"
              name="immatriculation"
              placeholder="Ex : AB-123-CD"
              value={form.immatriculation}
              onChange={handleChange}
              required
              autoComplete="off"
            />
            {errors.immatriculation && <span className="error-message">{errors.immatriculation}</span>}
          </div>

          <div className="form-section">
            <label htmlFor="constat" className="file-label">Joindre un constat (PDF)</label>
            <input
              type="file"
              name="constat"
              accept=".pdf"
              onChange={handleChange}
              className="custom-file-input"
            />
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

export default AccidentForm;
