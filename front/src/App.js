import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate, useLocation } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import Dashboard from './components/Dashboard/Dashboard';
import { AuthProvider, useAuth } from './components/auth/AuthContext';
import NavBar from './components/NavBar';
import Footer from './components/Footer';

const ProtectedRoute = ({ children }) => {
    const Auth = useAuth();
    const isAuthenticated = Auth.userIsAuthenticated();

    return isAuthenticated ? children : <Navigate to="/login" />;
};

const AppLayout = ({ children }) => {
    const location = useLocation();
    const isDashboard = location.pathname === '/dashboard';

    return (
        <div className="d-flex flex-column min-vh-100">
            {!isDashboard && <NavBar />}
            <main className="flex-grow-1">
                {children}
            </main>
            {!isDashboard && <Footer />}
        </div>
    );
};

function App() {
    return (
        <AuthProvider>
            <Router>
                <AppLayout>
                    <Routes>
                        <Route path="/" element={<HomePage />} />
                        <Route path="/login" element={<LoginPage />} />
                        <Route
                            path="/dashboard"
                            element={
                                <ProtectedRoute>
                                    <Dashboard />
                                </ProtectedRoute>
                            }
                        />
                    </Routes>
                </AppLayout>
            </Router>
        </AuthProvider>
    );
}

export default App;