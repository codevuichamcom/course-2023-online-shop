import { Outlet } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import { Footer, Header } from "src/components";

export const Layout = () => {
  return (
    <>
      <Header />
      <Outlet />
      <Footer />
      <ToastContainer />
    </>
  );
};
