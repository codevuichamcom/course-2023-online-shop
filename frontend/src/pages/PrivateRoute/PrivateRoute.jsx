import React from "react";
import { useSelector } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";

export const PrivateRoute = () => {
  const accountLoggedIn = useSelector((state) => state.account.loggedIn);
  if (accountLoggedIn.id) {
    //người dùng đã đăng nhặp
    return <Outlet />;
  } else {
    return <Navigate to="/login" />;
  }
};
