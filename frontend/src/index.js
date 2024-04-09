import "./App.css"
import {BrowserRouter, Route,  Routes} from "react-router-dom";
import ReactDOM from "react-dom/client";
import * as React from "react"
import Order from "./components/Order";
import ErrorPage from "./components/error-page";
import Static from "./components/Static";
import Layout from "./components/layout";


function App() {
    return (
        <Routes>
            <Route path={"/"} element={<Layout/>}>
                <Route index element={<Static/>}/>
                <Route path={"/order"} element={<Order/>}/>
                <Route path={"/*"} element={<ErrorPage/>}/>
            </Route>
        </Routes>
    )
}

ReactDOM.createRoot(document.getElementById("root"))
    .render(
        <React.StrictMode>
            <BrowserRouter>
                <App/>
            </BrowserRouter>
        </React.StrictMode>
    )