import axios from "axios"
import { BASE_URL_SERVER } from "../config/server"

const API_ENDPOINT = {
    LIST:"/api/items/all"
}

class ProductService {
    listProduct = async() => {
        return await axios.get(BASE_URL_SERVER + API_ENDPOINT.LIST)
    }
}
 const  productService = new ProductService()
export default productService