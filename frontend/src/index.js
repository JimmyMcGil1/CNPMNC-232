import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import ReactDOM from "react-dom/client";
import * as React from "react";
import Order from "./components/Order";
import ErrorPage from "./components/error-page";
import Static from "./components/Static";
import Layout from "./components/layout";
import ProductList from "./components/product-list";
import ShoppingCart from "./components/shopping-cart";
import InvoiceDetail from "./components/invoice-detail";
import Bill from "./components/bill";

function App() {
  return (
    <Routes>
      <Route path={"/"} element={<Layout />}>
        <Route index element={<Static />} />
        <Route path={"/order"} element={<Order />} />
        <Route path={"/*"} element={<ErrorPage />} />
        <Route path="/product/list" element={<ProductList />} />
        <Route path="/shopping-cart" element={<ShoppingCart />} />
        <Route path="/invoice-detail/:id" element={<InvoiceDetail />} />
        <Route path="/bill" element={<Bill />} />
      </Route>
    </Routes>
  );
}

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>
);
