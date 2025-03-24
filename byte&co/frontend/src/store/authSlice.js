import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const initialState = {
  user: JSON.parse(localStorage.getItem("user")) || null,
  loading: false,
  error: null,
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    loginStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    loginSuccess: (state, action) => {
      state.loading = false;
      state.user = action.payload;
      console.log("🔍 Utilisateur connecté :", state.user);
      localStorage.setItem("user", JSON.stringify(action.payload)); // Stocker l'utilisateur
    },
    loginFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    logout: (state) => {
      state.user = null;
      localStorage.removeItem("user");
    },
  },
});

export const { loginStart, loginSuccess, loginFailure, logout } =
  authSlice.actions;
export default authSlice.reducer;

export const loginUser = (credentials) => async (dispatch) => {
  dispatch(loginStart());
  try {
    const response = await axios.post(
      "http://localhost:8082/api/auth/login",
      credentials,
      { withCredentials: true }
    );
    dispatch(loginSuccess(response.data));
  } catch (error) {
    dispatch(
      loginFailure(error.response?.data?.message || "Erreur de connexion")
    );
  }
};

export const logoutUser = () => (dispatch) => {
  dispatch(logout());
};
