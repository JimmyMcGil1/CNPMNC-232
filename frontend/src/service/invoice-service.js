import axios from "axios";
import { BASE_URL_SERVER } from "../config/server";

const API_ENDPOINT = {
  CREATE: "/api/invoice/add?orderId=",
  DETAIL: "/api/order-item/all/items/",
};

class InvoiceService {
  create = async (data, id) => {
    return await axios.post(BASE_URL_SERVER + API_ENDPOINT.CREATE + id, data);
  };
  detail = async (id) => {
    return await axios.get(BASE_URL_SERVER + API_ENDPOINT.DETAIL + id);
  };
}
const invoiceService = new InvoiceService();
export default invoiceService;
