<<<<<<< HEAD
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from './AuthContext';
import AuthService from '../../services/AuthService';
import LoginImg from '../../assets/images/login.png';
import './Login.css';
=======
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { loginUser } from "../../store/authSlice";
import LoginImg from "../../assets/images/login.png";
import "./Login.css";
>>>>>>> 95ce1fb26650922af628aad2fc4964c7419601f1

const Login = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { loading, error } = useSelector((state) => state.auth);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    await dispatch(loginUser({ email: email, password: password }));
    navigate("/dashboard");
  };

<<<<<<< HEAD
    return (
        <div className="login-page">
        <div className="login-container">
            <div className="login-card">
                <div className="login-image">
                    <img src={LoginImg} alt="Welcome" />
                </div>
                <form onSubmit={handleLogin} className="login-form">
                    <h2>WELCOME</h2>
                    <div className="input-group">
                        <label htmlFor="email">Email</label>
                        <input
                            type="email"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            placeholder="Email"
                            required
                        />
                    </div>
                    <div className="input-group">
                        <label htmlFor="password">Password</label>
                        <input
                            type="password"
                            id="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder="Password"
                            required
                        />
                    </div>
                    <button type="submit" className="login-button">LOGIN</button>
                    {error && <div className="error-message">{error}</div>}
                </form>
=======
  return (
    <div className="login-page">
      <div className="login-container">
        <div className="login-card">
          <div className="login-image">
            <img src={LoginImg} alt="Welcome" />
          </div>
          <form onSubmit={handleLogin} className="login-form">
            <h2>WELCOME</h2>
            <div className="input-group">
              <label htmlFor="email">Email</label>
              <input
                type="email"
                id="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email"
                required
              />
>>>>>>> 95ce1fb26650922af628aad2fc4964c7419601f1
            </div>
            <div className="input-group">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                id="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Password"
                required
              />
            </div>
            <button type="submit" className="login-button" disabled={loading}>
              {loading ? "Connexion..." : "LOGIN"}
            </button>
            {error && <div className="error-message">{error}</div>}
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
