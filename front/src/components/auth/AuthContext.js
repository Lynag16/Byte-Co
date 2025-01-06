import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(() => {
        const storedUser = localStorage.getItem('user');
        return storedUser ? JSON.parse(storedUser) : null;
    });

    const userLogin = (details) => {
        localStorage.setItem('user', JSON.stringify(details));
        setUser(details);
    };
useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem('user'));
    if (storedUser) {
        setUser(storedUser);
    }
}, []);

    const userLogout = () => {
        localStorage.removeItem('user');
        setUser(null);
    };

    const userIsAuthenticated = () => !!user;
    const userHasRole = (role) => user?.roles?.includes(role);

    return (
        <AuthContext.Provider value={{ user, userLogin, userLogout, userIsAuthenticated, userHasRole }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);