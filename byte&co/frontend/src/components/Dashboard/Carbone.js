import React, { useState } from 'react';
import axios from 'axios';
import { Button, TextField, MenuItem, FormControl, InputLabel, Select, Grid, Snackbar } from '@mui/material';
import './EmpreinteCarboneForm.css';
import { useAuth } from '../auth/AuthContext';

const EmpreinteCarboneForm = () => {
  const { user } = useAuth();

  const idClient = user?.id;  // ID de l'utilisateur (client)
  const userEmail = user?.email;  // Email de l'utilisateur, si nécessaire

  const [dateTrajet, setDateTrajet] = useState('');
  const [pointDepart, setPointDepart] = useState('');
  const [pointArrivee, setPointArrivee] = useState('');
  const [moyenTransport, setMoyenTransport] = useState('');
  const [distance, setDistance] = useState(0);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState(false);

  // Fonction de gestion de la soumission du formulaire
  const handleSubmit = async (e) => {
    e.preventDefault();

    const trajetData = {
      idClient: parseInt(idClient),  // Conversion de l'ID en entier si nécessaire (si c'est un long côté back-end)
      dateTrajet,
      pointDepart,
      pointArrivee,
      moyenTransport,
      distance: parseFloat(distance),  // Conversion de la distance en nombre à virgule flottante
    };

    try {
      const response = await axios.post('http://localhost:8084/api/trajet/create', trajetData, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      console.log('Trajet créé avec succès:', response.data);
      setSuccess(true);
    } catch (err) {
      // Gestion de l'erreur : Si la réponse d'erreur est disponible, affichez-la
      if (err.response) {
        console.error('Erreur:', err.response.data);
        setError('Erreur lors de la soumission des données: ' + err.response.data.message);
      } else {
        console.error('Erreur:', err.message);
        setError('Erreur de réseau, veuillez réessayer plus tard.');
      }
    }
  };

  return (
    <div className="form-container">
      <h2>Calcul de l'empreinte carbone</h2>
      {error && <p className="error-message">{error}</p>}
      <form onSubmit={handleSubmit}>
        <Grid container spacing={2}>
          <Grid item xs={12} sm={6}>
            <TextField
              label="Date du trajet"
              type="datetime-local"
              fullWidth
              value={dateTrajet}
              onChange={(e) => setDateTrajet(e.target.value)}
              InputLabelProps={{
                shrink: true,
              }}
              required
            />
          </Grid>

          <Grid item xs={12} sm={6}>
            <TextField
              label="Point de départ"
              fullWidth
              value={pointDepart}
              onChange={(e) => setPointDepart(e.target.value)}
              required
            />
          </Grid>

          <Grid item xs={12} sm={6}>
            <TextField
              label="Point d'arrivée"
              fullWidth
              value={pointArrivee}
              onChange={(e) => setPointArrivee(e.target.value)}
              required
            />
          </Grid>

          <Grid item xs={12} sm={6}>
            <FormControl fullWidth required>
              <InputLabel>Moyen de transport</InputLabel>
              <Select
                value={moyenTransport}
                onChange={(e) => setMoyenTransport(e.target.value)}
                label="Moyen de transport"
              >
                <MenuItem value="Avion">Avion</MenuItem>
                <MenuItem value="Train">Train</MenuItem>
                <MenuItem value="Voiture">Voiture</MenuItem>
                <MenuItem value="Bus">Bus</MenuItem>
              </Select>
            </FormControl>
          </Grid>

          <Grid item xs={12} sm={6}>
            <TextField
              label="Distance (en km)"
              type="number"
              fullWidth
              value={distance}
              onChange={(e) => setDistance(e.target.value)}
              required
            />
          </Grid>

          <Grid item xs={12}>
            <Button type="submit" variant="contained" color="primary">
              Calculer l'empreinte carbone
            </Button>
          </Grid>
        </Grid>
      </form>

      <Snackbar
        open={success}
        autoHideDuration={6000}
        onClose={() => setSuccess(false)}
        message="Trajet créé avec succès !"
      />
    </div>
  );
};

export default EmpreinteCarboneForm;
