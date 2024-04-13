import React, { useEffect, useState } from "react";
import "../css/bill.css";
import { Container } from "@mui/material";
import invoiceService from "../service/invoice-service";

function Bill() {
  const [detailBill, setDetailBill] = useState([]);
  const orderId = localStorage.getItem("orderId");
  const dateNow = new Date();
  const generateRandomNumber = () => {
    return Math.floor(Math.random() * (9999999999 - 0 + 1)) + 0;
  };

  const getDetailBill = async () => {
    await invoiceService
      .detail(orderId)
      .then((res) => {
        console.log(res.data);
        setDetailBill(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    getDetailBill();
  }, [orderId]);

  const convertDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    const formattedDate = `${day}/${month}/${year}`;
    return formattedDate;
  };

  const randomNumber = generateRandomNumber();

  const billDate = convertDate(dateNow);

  return (
    <Container maxWidth="lg" sx={{ backgroundColor: "white" }}>
      <div id="invoice">
        <div className="invoice overflow-auto">
          <div style={{ minWidth: 600 }}>
            <header style={{ display: "flex", alignItems: "center" }}>
              <div className="col">
                <span rel="noreferrer">
                  <img
                    src="https://static.vecteezy.com/system/resources/previews/020/194/750/original/invoice-icon-for-your-website-design-logo-app-ui-free-vector.jpg"
                    width={150}
                    style={{ marginRight: "10px" }}
                    alt=""
                  />
                </span>
              </div>
              <div className="col company-details" style={{ flexGrow: 1 }}>
                <h2 className="name">
                  <span style={{ textDecoration: "none" }} rel="noreferrer">
                    Arboshiki
                  </span>
                </h2>
                <div style={{ marginBottom: "5px" }}>
                  455 Foggy Heights, AZ 85004, US
                </div>
                <div style={{ marginBottom: "5px" }}>(123) 456-789</div>
                <div>company@example.com</div>
              </div>
            </header>
            <main>
              <div
                style={{
                  display: "flex",
                  alignItems: "center",
                  marginBottom: "30px",
                }}
              >
                <div className="col invoice-to">
                  <div className="text-gray-light">INVOICE TO:</div>
                  <h2 className="to">user admin</h2>
                  <div className="address">
                    796 Silver Harbour, TX 79273, US
                  </div>
                  <div className="email">
                    <p>john@example.com</p>
                  </div>
                </div>
                <div className="col company-details" style={{ flexGrow: 1 }}>
                  <div
                    className="col invoice-details"
                    style={{ textAlign: "right" }}
                  >
                    <h1 className="invoice-id" style={{ marginBottom: "5px" }}>
                      INVOICE Number: {randomNumber}
                    </h1>
                    <div className="date">Due Date: {billDate}</div>
                  </div>
                </div>
              </div>
              <table border={0} cellSpacing={0} cellPadding={0}>
                <thead>
                  <tr>
                    <th>#</th>
                    <th className="text-left">Order Id</th>
                    <th className="text-right">Item Id</th>
                    <th className="text-right">Price</th>
                    <th className="text-right">Amount</th>
                  </tr>
                </thead>
                <tbody>
                  {detailBill.map((bill, index) => (
                    <tr key={bill.id}>
                      <td className="no">{index + 1}</td>
                      <td className="text-left">
                        <p>{bill.idOrder}</p>
                      </td>
                      <td className="unit">{bill.idItem}</td>
                      <td className="qty">{bill.salePrice}</td>
                      <td className="total">{bill.amount}</td>
                    </tr>
                  ))}
                </tbody>
                <tfoot>
                  <tr>
                    <td colSpan={2} />
                    <td colSpan={2}>SUBTOTAL</td>
                    <td>$5,200.00</td>
                  </tr>
                  <tr>
                    <td colSpan={2} />
                    <td colSpan={2}>TAX 25%</td>
                    <td>$1,300.00</td>
                  </tr>
                  <tr>
                    <td colSpan={2} />
                    <td colSpan={2}>GRAND TOTAL</td>
                    <td>$6,500.00</td>
                  </tr>
                </tfoot>
              </table>
              <div className="thanks">Thank you!</div>
              <div className="notices">
                <div>NOTICE:</div>
                <div className="notice">
                  A finance charge of 1.5% will be made on unpaid balances after
                  30 days.
                </div>
              </div>
            </main>
          </div>
          <div />
        </div>
      </div>
    </Container>
  );
}

export default Bill;
