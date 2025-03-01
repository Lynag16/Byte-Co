import React from "react";
import HomePage from "./pages/home/HomePage";
import Offres from "./pages/offres/OffresPage";
import Dashboard from "./components/Dashboard/Dashboard";
import Login from "./components/auth/Login";
import NavBar from "./components/Shared/NavBar";
import Footer from "./components/Shared/Footer";
import { useSelector } from "react-redux";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
  useLocation,
} from "react-router-dom";

const ProtectedRoute = ({ children }) => {
  const { user } = useSelector((state) => state.auth);
  return user ? children : <Navigate to="/login" />;
};

const AppLayout = ({ children }) => {
  const location = useLocation();
  const isDashboard = location.pathname === "/dashboard";

  return (
    <div className="d-flex flex-column min-vh-100">
      {!isDashboard && <NavBar />}
      <main className="flex-grow-1">{children}</main>
      {!isDashboard && <Footer />}
    </div>
  );
};

function App() {
  return (
    <Router>
      <AppLayout>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<Login />} />
          <Route
            path="/dashboard"
            element={
              <ProtectedRoute>
                <Dashboard />
              </ProtectedRoute>
            }
          />
          <Route path="/offres" element={<Offres />} />
        </Routes>
      </AppLayout>
    </Router>
  );
}

export default App;
