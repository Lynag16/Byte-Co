import React, { createContext, useContext, useState, useEffect } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const getStoredUser = () => {
    const local = localStorage.getItem("user");
    const session = sessionStorage.getItem("user");
    return local ? JSON.parse(local) : session ? JSON.parse(session) : null;
  };

  const [user, setUser] = useState(getStoredUser());

  useEffect(() => {
    const stored = getStoredUser();
    if (stored) setUser(stored);
  }, []);

  const userLogin = (userDetails) => {
    setUser(userDetails);
  };

  const userLogout = () => {
    localStorage.removeItem("user");
    sessionStorage.removeItem("user");
    setUser(null);
  };

  const userIsAuthenticated = () => !!user?.token;

  const userHasRole = (role) => user?.role === role;

  return (
    <AuthContext.Provider
      value={{
        user,
        userLogin,
        userLogout,
        userIsAuthenticated,
        userHasRole,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);