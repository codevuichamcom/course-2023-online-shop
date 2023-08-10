import "bootstrap/dist/css/bootstrap.min.css";
import "react-toastify/dist/ReactToastify.css";
import * as ReactDOM from "react-dom/client";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Layout } from "src/Layout";
import "src/global.css";
import { HomePage } from "src/pages/HomePage";
import { LoginPage } from "src/pages/LoginPage";
import { NotFound } from "src/pages/NotFound/NotFound";
import { ProductDetailPage } from "src/pages/ProductDetailPage";
import { ShopCategoryPage } from "src/pages/ShopCategoryPage";
import { RegisterPage } from "src/pages/RegisterPage";
import { store } from "src/app/store";
import { Provider } from "react-redux";
import { BlogPage } from "./pages/BlogPage";
import { PrivateRoute } from "./pages/PrivateRoute";
import { CartPage } from "./pages/CartPage";

ReactDOM.createRoot(document.getElementById("root")).render(
  <Provider store={store}>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route path="home" index element={<HomePage />} />
          <Route
            path="product-detail/:productId"
            element={<ProductDetailPage />}
          />
          <Route path="login" element={<LoginPage />} />
          <Route path="register" element={<RegisterPage />} />
          <Route path="shop-category" element={<ShopCategoryPage />} />
          <Route path="cart" element={<CartPage />} />
          <Route path="/" element={<PrivateRoute />}>
            <Route path="blog" element={<BlogPage />} />
          </Route>
          <Route path="*" element={<NotFound />} />
        </Route>
      </Routes>
    </BrowserRouter>
  </Provider>
);
