import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
  Box, Chip, Button, Paper, Dialog, DialogTitle, DialogContent, IconButton, Typography
} from '@mui/material';
import { DataGrid } from '@mui/x-data-grid';
import { Close } from '@mui/icons-material';
import { useAuth } from '../../auth/AuthContext';
import { motion } from 'framer-motion';
import { PieChart, Pie, BarChart, Bar, XAxis, YAxis, Tooltip, Legend, ResponsiveContainer, Cell } from 'recharts';
import {
  FaCarCrash, FaShieldAlt, FaPhoneAlt, FaMedkit, FaBed
} from 'react-icons/fa';
import './MesSinistres.css';

const iconMap = {
  ACCIDENT_ROUTE: <FaCarCrash className="icon" />,
  VOL_OU_PERTE_OBJET: <FaShieldAlt className="icon" />,
  RETARD_TRANSPORT: <FaPhoneAlt className="icon" />,
  INCIDENT_MEDICAL: <FaMedkit className="icon" />,
  PROBLEME_HEBERGEMENT: <FaBed className="icon" />,
};

const typeLabel = {
  ACCIDENT_ROUTE: 'Accident',
  VOL_OU_PERTE_OBJET: 'Vol / Perte',
  RETARD_TRANSPORT: 'Retard',
  INCIDENT_MEDICAL: 'Médical',
  PROBLEME_HEBERGEMENT: 'Hébergement',
};

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042', '#ab47bc'];

const StatsParType = ({ sinistres }) => {
  const count = sinistres.reduce((acc, s) => {
    acc[s.type] = (acc[s.type] || 0) + 1;
    return acc;
  }, {});
  const data = Object.entries(count).map(([type, value]) => ({
    name: typeLabel[type], value,
  }));

  return (
    <Box className="stats-container">
      <Typography variant="h6">Répartition par type</Typography>
      <ResponsiveContainer width="100%" height={300}>
        <PieChart>
          <Pie dataKey="value" data={data} cx="50%" cy="50%" outerRadius={100} label>
            {data.map((_, i) => (
              <Cell key={i} fill={COLORS[i % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip />
          <Legend />
        </PieChart>
      </ResponsiveContainer>
    </Box>
  );
};

const StatsParStatut = ({ sinistres }) => {
  const count = sinistres.reduce((acc, s) => {
    acc[s.statut] = (acc[s.statut] || 0) + 1;
    return acc;
  }, {});
  const data = Object.entries(count).map(([statut, nombre]) => ({ statut, nombre }));

  return (
    <Box className="stats-container">
      <Typography variant="h6">Répartition par statut</Typography>
      <ResponsiveContainer width="100%" height={250}>
        <BarChart layout="vertical" data={data}>
          <XAxis type="number" />
          <YAxis dataKey="statut" type="category" />
          <Tooltip />
          <Bar dataKey="nombre" fill="#42a5f5" />
        </BarChart>
      </ResponsiveContainer>
    </Box>
  );
};

const TousLesSinistresAdmin = () => {
  const { user } = useAuth();
  const [sinistres, setSinistres] = useState([]);
  const [previewUrl, setPreviewUrl] = useState('');
  const [openPreview, setOpenPreview] = useState(false);

  const fetchSinistres = async () => {
    try {
      const res = await axios.get('http://localhost:8085/api/admin/sinistres', {
        headers: { Authorization: `Bearer ${user?.token}` },
      });
      setSinistres(res.data);
    } catch (e) {
      console.error(e);
    }
  };

  useEffect(() => { fetchSinistres(); }, []);

  const handleAction = async (id, action) => {
    try {
      await axios.put(`http://localhost:8085/api/admin/sinistres/${id}/${action}`, {}, {
        headers: { Authorization: `Bearer ${user?.token}` },
      });
      fetchSinistres();
    } catch (err) {
      console.error('Action erreur', err);
    }
  };

  const columns = [
    {
      field: 'type',
      headerName: 'Type',
      flex: 1,
      renderCell: (params) => (
        <Chip icon={iconMap[params.value]} label={typeLabel[params.value]} className="chip-type" />
      ),
    },
    { field: 'dateDeclaration', headerName: 'Date', flex: 1 },
    { field: 'description', headerName: 'Description', flex: 2 },
    {
      field: 'statut',
      headerName: 'Statut',
      flex: 1,
      renderCell: (params) => (
        <Chip label={params.value} className="chip-statut" />
      ),
    },
    {
      field: 'userId',
      headerName: 'Déclarant',
      flex: 1,
    },
    {
      field: 'actions',
      headerName: 'Actions',
      flex: 2,
      renderCell: ({ row }) => (
        <Box display="flex" gap={1}>
          <Button variant="outlined" size="small" onClick={() => handleAction(row.id, 'traiter')}>Traiter</Button>
          <Button variant="outlined" size="small" onClick={() => handleAction(row.id, 'cloturer')}>Clôturer</Button>
          <Button variant="outlined" size="small" onClick={() => handleAction(row.id, 'reouvrir')}>Réouvrir</Button>
          <Button variant="contained" size="small" disabled>🎯 Affecter</Button>
        </Box>
      ),
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
                  Tous les sinistres
                </motion.h2>
        <DataGrid
          rows={sinistres}
          columns={columns}
          getRowId={(row) => row.id}
          pageSize={10}
          rowsPerPageOptions={[10, 20, 30]}
          autoHeight
        />
        {sinistres.length > 0 && (
          <>
            <StatsParType sinistres={sinistres} />
            <StatsParStatut sinistres={sinistres} />
          </>
        )}
      </Paper>

      {/* Aperçu PDF */}
      <Dialog open={openPreview} onClose={() => setOpenPreview(false)} maxWidth="md" fullWidth>
        <DialogTitle>
          Aperçu du fichier
          <IconButton onClick={() => setOpenPreview(false)} sx={{ position: 'absolute', right: 8, top: 8 }}>
            <Close />
          </IconButton>
        </DialogTitle>
        <DialogContent sx={{ height: '80vh', p: 0 }}>
          <iframe src={previewUrl} title="Preview PDF" width="100%" height="100%" style={{ border: 'none' }} />
        </DialogContent>
      </Dialog>
    </Box>
  );
};

export default TousLesSinistresAdmin;
