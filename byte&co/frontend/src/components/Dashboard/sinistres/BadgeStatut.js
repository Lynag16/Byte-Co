const BadgeStatut = ({ statut }) => {
    const couleurs = {
      EN_ATTENTE: "orange",
      VALIDE: "green",
      REJETE: "red"
    };
  
    return (
      <span style={{
        backgroundColor: couleurs[statut] || "gray",
        color: "white",
        padding: "4px 8px",
        borderRadius: "8px",
        fontSize: "0.85rem"
      }}>
        {statut}
      </span>
    );
  };
  
  export default BadgeStatut;
  