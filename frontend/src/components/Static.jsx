import React, { useEffect, useState } from "react";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import AlertNotification from "../shared/alert";
import api from "../api/api";
function Static() {
  const [totalAmount, setTotalAmount] = useState(null);
  const [openAlert, setOpenAlert] = useState(false);
  const [title, setTitle] = useState("");

  useEffect(() => {
    const fetchTotalAmount = async () => {
      try {
        
       const getData = await api.get("/total-invoice-amount");
        const data = await getData.data;
        setTotalAmount(data.totalAmount);
        console.log(data.totalAmount);
      } catch (error) {
        console.error("Error fetching total amount:", error);
        setOpenAlert(true);
        setTitle("Error fetching total amount");
      }
    };

    fetchTotalAmount();
  }, []);

  return (
    <>
      <Container maxWidth="lg">
        <Typography variant="h3" gutterBottom>
          Thống kê tổng số hóa đơn
        </Typography>
        <Typography variant="body1">
          Tổng số tiền hóa đơn: {totalAmount !== null ? totalAmount : "Loading..."}
        </Typography>
      </Container>
      <AlertNotification
        open={openAlert}
        handleClose={() => setOpenAlert(false)}
        title={title}
      />
    </>
  );
}

export default Static;
