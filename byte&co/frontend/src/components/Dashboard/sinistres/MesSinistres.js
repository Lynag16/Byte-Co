import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { DataGrid } from '@mui/x-data-grid';
import { useAuth } from '../../auth/AuthContext';
import {
  Box, Chip, Typography, Button, Paper,
  Dialog, DialogTitle, DialogContent, IconButton
} from '@mui/material';
import { Close } from '@mui/icons-material';
import { motion } from 'framer-motion';
import { frFR } from '@mui/x-data-grid/locales';


import {
  FaCarCrash, FaShieldAlt, FaPhoneAlt, FaMedkit, FaBed
} from 'react-icons/fa';
import './MesSinistres.css';
import { BarChart, Bar, XAxis, YAxis,  PieChart, Pie, Cell, Legend, ResponsiveContainer, Tooltip } from 'recharts';


const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042', '#ab47bc'];

const StatsSinistres = ({ sinistres }) => {
  const countByType = sinistres.reduce((acc, s) => {
    acc[s.type] = (acc[s.type] || 0) + 1;
    return acc;
  }, {});

  const data = Object.entries(countByType).map(([type, count]) => ({
    name: typeLabel[type] || type,
    value: count,
  }));

  return (
    <Box className="stats-container">
      <Typography variant="h6" gutterBottom>Répartition des sinistres</Typography>
      <ResponsiveContainer width="100%" height={300}>
        <PieChart>
          <Pie
            dataKey="value"
            data={data}
            cx="50%" cy="50%"
            outerRadius={100}
            fill="#8884d8"
            label
          >
            {data.map((_, index) => (
              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip />
          <Legend verticalAlign="bottom" />
        </PieChart>
      </ResponsiveContainer>
    </Box>
  );
};


const StatutsSinistresBarChart = ({ sinistres }) => {
  const countByStatut = sinistres.reduce((acc, s) => {
    acc[s.statut] = (acc[s.statut] || 0) + 1;
    return acc;
  }, {});

  const data = Object.entries(countByStatut).map(([statut, count]) => ({
    statut,
    nombre: count,
  }));

  return (
    <Box className="stats-container">
      <Typography variant="h6" gutterBottom>Répartition par statut</Typography>
      <ResponsiveContainer width="100%" height={250}>
        <BarChart layout="vertical" data={data}>
          <XAxis type="number" allowDecimals={false} />
          <YAxis dataKey="statut" type="category" />
          <Tooltip />
          <Bar dataKey="nombre" fill="#42a5f5" />
        </BarChart>
      </ResponsiveContainer>
    </Box>
  );
};

const iconMap = {
  ACCIDENT_ROUTE: <FaCarCrash className="icon accident" />,
  VOL_OU_PERTE_OBJET: <FaShieldAlt className="icon vol" />,
  RETARD_TRANSPORT: <FaPhoneAlt className="icon retard" />,
  INCIDENT_MEDICAL: <FaMedkit className="icon medical" />,
  PROBLEME_HEBERGEMENT: <FaBed className="icon hebergement" />,
};

const typeLabel = {
  ACCIDENT_ROUTE: 'Accident',
  VOL_OU_PERTE_OBJET: 'Vol / Perte',
  RETARD_TRANSPORT: 'Retard',
  INCIDENT_MEDICAL: 'Médical',
  PROBLEME_HEBERGEMENT: 'Hébergement',
};

const MesSinistres = () => {
  const { user } = useAuth();
  const [sinistres, setSinistres] = useState([]);
  const [openDetails, setOpenDetails] = useState(false);
  const [selectedSinistre, setSelectedSinistre] = useState(null);
  const [openPreview, setOpenPreview] = useState(false);
  const [previewUrl, setPreviewUrl] = useState('');

  const handleOpenPreview = (path, baseUrl) => {
    const filename = path?.split('/').pop();
    if (filename) {
      setPreviewUrl(`${baseUrl}/${filename}`);
      setOpenPreview(true);
    }
  };

  const handleClosePreview = () => {
    setOpenPreview(false);
    setPreviewUrl('');
  };

  const renderDetails = () => {
    if (!selectedSinistre) return null;
    const { type } = selectedSinistre;
    switch (type) {
      case 'ACCIDENT_ROUTE':
        return (
          <>
            <p><strong>Lieu :</strong> {selectedSinistre.lieuAccident}</p>
            <p><strong>Immatriculation :</strong> {selectedSinistre.immatriculation}</p>
          </>
        );
      case 'VOL_OU_PERTE_OBJET':
        return (
          <>
            <p><strong>Lieu :</strong> {selectedSinistre.lieuVol}</p>
            <p><strong>Objet :</strong> {selectedSinistre.descriptionObjetPerdu}</p>
            <p><strong>Valeur :</strong> {selectedSinistre.valeurObjetPerdu} €</p>
          </>
        );
      case 'INCIDENT_MEDICAL':
        return (
          <>
            <p><strong>Symptômes :</strong> {selectedSinistre.symptomes}</p>
            <p><strong>Type intervention :</strong> {selectedSinistre.typeIntervention}</p>
            <p><strong>Coût :</strong> {selectedSinistre.coutIntervention} €</p>
          </>
        );
      case 'RETARD_TRANSPORT':
        return (
          <>
            <p><strong>Moyen de transport :</strong> {selectedSinistre.moyenTransport}</p>
            <p><strong>Durée :</strong> {selectedSinistre.dureeRetardMinutes} minutes</p>
          </>
        );
      case 'PROBLEME_HEBERGEMENT':
        return (
          <>
            <p><strong>Hôtel :</strong> {selectedSinistre.nomHotel}</p>
            <p><strong>Nature problème :</strong> {selectedSinistre.natureProbleme}</p>
          </>
        );
      default:
        return <p>Aucun détail disponible.</p>;
    }
  };

  useEffect(() => {
    const fetchSinistres = async () => {
      try {
        const response = await axios.get('http://localhost:8085/api/mes-sinistres', {
          headers: { Authorization: `Bearer ${user?.token}` },
        });
        setSinistres(response.data);
      } catch (err) {
        console.error('Erreur lors du chargement des sinistres', err);
      }
    };
    fetchSinistres();
  }, [user]);

  const handleOpenDetails = (row) => {
    setSelectedSinistre(row);
    setOpenDetails(true);
  };

  const handleCloseDetails = () => {
    setOpenDetails(false);
    setSelectedSinistre(null);
  };

  const columns = [
    {
      field: 'type',
      headerName: 'Type',
      flex: 1,
      renderCell: (params) => (
        <Chip
          icon={iconMap[params.value]}
          label={typeLabel[params.value] || params.value}
          variant="outlined"
          className="chip-type"
        />
      ),
    },
    {
      field: 'dateDeclaration',
      headerName: 'Date',
      flex: 1,
      headerAlign: 'center',
      align: 'center',
    },
    {
      field: 'description',
      headerName: 'Description',
      flex: 2,
    },
    {
      field: 'statut',
      headerName: 'Statut',
      flex: 1,
      renderCell: (params) => (
        <Chip
          label={params.value}
          color={
            params.value === 'EN_COURS' ? 'warning' :
            params.value === 'CLOTURE' ? 'success' : 'default'
          }
          className="chip-statut"
        />
      ),
    },
    {
          field: 'voirDetails', headerName: 'Détails', flex: 1,
          renderCell: ({ row }) => (
            <Button variant="outlined" size="small" onClick={() => handleOpenDetails(row)}>
              Voir détails
            </Button>
          ),
        },
    {
      field: 'fichiers',
      headerName: 'Pièces jointes',
      flex: 1.5,
      renderCell: ({ row }) => {
        const fileLinks = {
          ACCIDENT_ROUTE: row.constatFilePath && (
            <Button
              variant="outlined"
              size="small"
              onClick={() => handleOpenPreview(row.constatFilePath, 'http://localhost:8085/api/sinistres/uploads/constats')}
            >
              📄 Constat
            </Button>
          ),
          VOL_OU_PERTE_OBJET: row.declarationPoliceFilePath && (
            <Button
              variant="outlined"
              size="small"
              onClick={() => handleOpenPreview(row.declarationPoliceFilePath, 'http://localhost:8085/api/sinistres/uploads/declarationPolices')}
            >
              📄 Déclaration
            </Button>
          ),
          INCIDENT_MEDICAL: row.dossierMedicalFilePath && (
            <Button
              variant="outlined"
              size="small"
              onClick={() => handleOpenPreview(row.dossierMedicalFilePath, 'http://localhost:8085/api/sinistres/uploads/dossierMedicaux')}
            >
              📄 Médical
            </Button>
          ),
        };
        return fileLinks[row.type] || <span className="aucun-lien">Aucun</span>;
      },
    },
  ];

  return (
    <Box className="mes-sinistres-wrapper">
      <Paper className="mes-sinistres-paper">
        <motion.h2
          initial={{ opacity: 0, y: -20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5 }}
          className="declaration-sinistre-title"
        >
          Mes Sinistres
        </motion.h2>
        <DataGrid
          rows={sinistres}
          columns={columns}
          getRowId={(row) => row.id}
          pageSize={8}
          rowsPerPageOptions={[8, 16, 24]}
          disableRowSelectionOnClick
          autoHeight
          localeText={frFR.components.MuiDataGrid.defaultProps.localeText}
          className="mes-sinistres-table"
        />

        {sinistres.length > 0 && (
          <>
            <StatsSinistres sinistres={sinistres} />
            <StatutsSinistresBarChart sinistres={sinistres} />
          </>
        )}

      </Paper>

      <Dialog open={openDetails} onClose={handleCloseDetails} maxWidth="sm" fullWidth>
              <DialogTitle>
                Détails du sinistre
                <IconButton onClick={handleCloseDetails} sx={{ position: 'absolute', right: 8, top: 8 }}>
                  <Close />
                </IconButton>
              </DialogTitle>
              <DialogContent dividers>
                {selectedSinistre && (
                  <>
                    <p><strong>Description :</strong> {selectedSinistre.description}</p>
                    <p><strong>Date :</strong> {selectedSinistre.dateDeclaration}</p>
                    {renderDetails()}
                  </>
                )}
              </DialogContent>
            </Dialog>

      {/* Aperçu PDF */}
      <Dialog open={openPreview} onClose={handleClosePreview} maxWidth="md" fullWidth>
        <DialogTitle>
          Aperçu du fichier
          <IconButton
            onClick={handleClosePreview}
            sx={{ position: 'absolute', right: 8, top: 8 }}
          >
            <Close />
          </IconButton>
        </DialogTitle>
        <DialogContent dividers sx={{ height: '80vh', p: 0 }}>
          <iframe
            src={previewUrl}
            title="Aperçu PDF"
            width="100%"
            height="100%"
            style={{ border: 'none' }}
          />
        </DialogContent>
      </Dialog>
    </Box>
  );
};

export default MesSinistres;
