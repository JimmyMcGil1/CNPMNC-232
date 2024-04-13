import React, { useEffect, useState } from "react";
import ImageList from "@mui/material/ImageList";
import ImageListItem from "@mui/material/ImageListItem";
import ImageListItemBar from "@mui/material/ImageListItemBar";
import Container from "@mui/material/Container";
import productService from "../service/product-service";
import { Button, Typography } from "@mui/material";
import AlertNotification from "../shared/alert";

function ProductList() {
  const [productRespone, setProductRespone] = useState([]);
  const [openAlert, setOpenAlert] = useState(false);
  const [title, setTitle] = useState("");

  const getListProduct = async () => {
    await productService
      .listProduct()
      .then((res) => {
        setProductRespone(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    getListProduct();
  }, []);

  const handleAddToCart = (item) => {
    let cartItems = JSON.parse(localStorage.getItem("cart")) || [];
    const isItemInCart = cartItems.some((cartItem) => cartItem.id === item.id);
    if (isItemInCart) {
      setOpenAlert(true);
      setTitle("Sản phẩm " + item.item_name + " đã có trong giỏ hàng");
      return;
    }
    cartItems = cartItems.concat(item);
    localStorage.setItem("cart", JSON.stringify(cartItems));
    setOpenAlert(true);
    setTitle("Đã thêm " + item.item_name + " vào giỏ hàng");
  };

  return (
    <>
      <Container maxWidth="lg">
        <ImageList>
          {productRespone.map((item) => (
            <ImageListItem key={item.id}>
              <img
                src="https://cdn.pixabay.com/photo/2016/10/14/18/21/tee-1740871_1280.jpg"
                alt={item.title}
                loading="lazy"
                width={200}
                height={200}
              />
              <ImageListItemBar
                title={"Tên sản phẩm: " + item.item_name}
                subtitle={<Typography>size: {item.size}</Typography>}
                position="below"
              />
              <Button variant="contained" onClick={() => handleAddToCart(item)}>
                Add to cart
              </Button>
            </ImageListItem>
          ))}
        </ImageList>
      </Container>
      <AlertNotification
        open={openAlert}
        handleClose={() => setOpenAlert(false)}
        title={title}
      />
    </>
  );
}

export default ProductList;
