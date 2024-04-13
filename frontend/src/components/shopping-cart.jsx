import React, { useState } from "react";
import "../css/shopping-cart.css";
import Container from "@mui/material/Container";
import Button from "@mui/material/Button";
import { Box, Tooltip } from "@mui/material";
import ButtonGroup from "@mui/material/ButtonGroup";
import { Link, useNavigate } from "react-router-dom";
import orderService from "../service/order-service";
import LoadingButton from "@mui/lab/LoadingButton";

function ShoppingCart() {
  const getItem = localStorage.getItem("cart");
  const dataArray = JSON.parse(getItem);
  const [count, setCount] = useState(1);
  const [isLoading, setIsLoading] = useState(false);
  const dateOrder = new Date();
  const router = useNavigate();

  const handleClickCount = () => {
    setCount(count + 1);
  };

  const handlePrevCount = () => {
    if (count > 1) {
      setCount(count - 1);
    }
  };

  const buttons = [
    <Button key="one" disabled={count === 1} onClick={handlePrevCount}>
      -
    </Button>,
    <Button key="two">{count}</Button>,
    <Button key="three" onClick={handleClickCount}>
      +
    </Button>,
  ];

  const generateRandomNumber = () => {
    return Math.floor(Math.random() * (999 - 0 + 1)) + 0;
  };

  const randomNumber = generateRandomNumber();

  const handleSubmitOrder = async () => {
    const dataSubmit = {
      orderDate: dateOrder,
      supplier: 1,
      deposit: randomNumber,
      statusOrder: "1",
      totalCost: randomNumber,
    };
    setIsLoading(true);
    try {
      const orderResponse = await orderService.createOrder(dataSubmit);
      const newOrderId = orderResponse.data.newOrderId;
      if (newOrderId > 0) {
        const dataItemOrder = {
          itemId: dataArray[0].id,
          orderId: newOrderId,
          salePrice: 10,
          amount: 1000,
        };
        await orderService.addItemToOrderDetail(dataItemOrder);
        router("/invoice-detail/" + newOrderId);
      }
    } catch (err) {
      console.log(err);
    } finally {
      setIsLoading(false);
    }
  };

  const [cartItems, setCartItems] = useState(
    JSON.parse(localStorage.getItem("cart")) || []
  );

  const removeItemById = (items, id) => {
    const updatedItems = items.filter((item) => item.id !== id);
    setCartItems(updatedItems);
    localStorage.setItem("cart", JSON.stringify(updatedItems));
    return updatedItems;
  };

  const emptyImage =
    "https://i.ibb.co/p67yn7k/00dc9553da100e6db815d9b0a68922c6.png";

  return (
    <Container maxWidth="xl">
      <section className="cart_wrapper" style={{ minHeight: "30rem" }}>
        {dataArray.length === 0 ? (
          <div
            style={{
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <img src={emptyImage} alt="Empty Image" />
            <div className="footer mb-4">
              <Link to="/product/list">
                <div className="flex cursor-pointer">
                  <span className="material-icons-outlined">west</span>
                  <div>
                    <p>Continue Shopping</p>
                  </div>
                </div>
              </Link>
            </div>
          </div>
        ) : (
          <>
            <div className="cart_lists" style={{ backgroundColor: "white" }}>
              <div className="cart_title">Shopping Cart</div>
              <div className="cart_list_wrap">
                <div className="cart_responsive">
                  {dataArray?.map((item) => (
                    <div className="tr_item" key={item.id}>
                      <div className="td_item item_img">
                        <img
                          src="https://cdn.pixabay.com/photo/2016/10/14/18/21/tee-1740871_1280.jpg"
                          alt="cart"
                        />
                      </div>
                      <div className="td_item item_name">
                        <label className="main">{item.item_name}</label>
                        <label className="sub">{item.size}</label>
                      </div>
                      <div className="td_item item_color">
                        <label>Blue</label>
                      </div>
                      <div className="td_item item_qty">
                        <Box
                          sx={{
                            display: "flex",
                            flexDirection: "column",
                            alignItems: "center",
                            "& > *": {
                              m: 1,
                            },
                          }}
                        >
                          <ButtonGroup
                            size="small"
                            aria-label="Small button group"
                          >
                            {buttons}
                          </ButtonGroup>
                        </Box>
                      </div>
                      <div className="td_item item_price">
                        <label>$ 260.00</label>
                      </div>
                      <div className="td_item item_remove">
                        <Tooltip title="remove item">
                          <span
                            className="material-icons-outlined"
                            onClick={() => removeItemById(dataArray, item.id)}
                          >
                            close
                          </span>
                        </Tooltip>
                      </div>
                    </div>
                  ))}
                </div>
                <div className="footer">
                  <div className="back_cart">
                    <Link to="/product/list">
                      <span className="material-icons-outlined">west</span>
                      Continue Shopping
                    </Link>
                  </div>
                </div>
              </div>
            </div>
            <div className="cart_details">
              <div
                className="cart_title"
                style={{ color: "#94A3B8", marginBottom: "10px" }}
              >
                Order Summary
              </div>
              <div>
                <label>Subtotal: </label>
                <strong>$2451.00</strong>
              </div>
              <div>
                <label>Items: </label>
                <strong>{cartItems.length}</strong>
              </div>
              <div className="mt-12">
                <img
                  src="https://i.ibb.co/fdY2pK2/98798b7662d8a4b21cb66ad4bd430b48.jpg"
                  alt="product"
                />
              </div>
              <div className="form_row">
                <LoadingButton
                  className="btn"
                  onClick={handleSubmitOrder}
                  loading={isLoading}
                >
                  Submit Order
                </LoadingButton>
              </div>
            </div>
          </>
        )}
      </section>
    </Container>
  );
}

export default ShoppingCart;
