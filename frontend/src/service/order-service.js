import axios from "axios";
import { BASE_URL_SERVER } from "../config/server";

const API_ENDPOINT = {
  CREATE: "/api/orders/add",
  ADD_ITEM: "/api/order-item/add",
};

class OrderService {
  createOrder = async (data) => {
    return await axios.post(BASE_URL_SERVER + API_ENDPOINT.CREATE, data);
  };
  addItemToOrderDetail = async (data) => {
    return await axios.post(BASE_URL_SERVER + API_ENDPOINT.ADD_ITEM, data);
  };
}
const orderService = new OrderService();
export default orderService;
