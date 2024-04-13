import React, { useEffect, useState } from "react";
import "../css/invoice.css";
import { Stack, Button, Container } from "@mui/material";
import { useParams, useNavigate } from "react-router-dom";
import invoiceService from "../service/invoice-service";

function InvoiceDetail() {
  const { id } = useParams();
  const [detailInvoice, setDetailInvoice] = useState([]);
  const dateNow = new Date();
  const router = useNavigate();

  useEffect(() => {
    if (id) {
      localStorage.setItem("orderId", id);
    }
  }, [id]);

  const handlePrintData = async () => {
    const dataPrint = {
      order_id: id,
      invoiceDate: dateNow,
      totalCost: detailInvoice[0].amount,
      changeAmount: detailInvoice[0].idItem,
    };
    await invoiceService
      .create(dataPrint, id)
      .then((res) => {
        console.log(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
    router("/bill");
  };

  const getDetailInvoice = async () => {
    await invoiceService
      .detail(id)
      .then((res) => {
        console.log(res.data);
        setDetailInvoice(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    getDetailInvoice();
  }, [id]);

  const convertDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    const formattedDate = `${day}/${month}/${year}`;
    return formattedDate;
  };

  const billDate = convertDate(dateNow);

  const generateRandomNumber = () => {
    return Math.floor(Math.random() * (9999999999 - 0 + 1)) + 0;
  };

  const randomNumber = generateRandomNumber();

  return (
    <Container maxWidth="lg" sx={{ backgroundColor: "white" }}>
      <Stack
        sx={{ padding: 2 }}
        direction="column-reverse"
        justifyContent="space-evenly"
        alignItems="flex-end"
      >
        <Button variant="contained" onClick={handlePrintData}>
          Invoice
        </Button>
      </Stack>
      <div className="invoice-box">
        <table cellPadding={0} cellSpacing={0}>
          <tbody>
            <tr className="top_rw">
              <td colSpan={2}>
                <h2 style={{ marginBottom: 0 }}>Order Id: {id}</h2>
                <span>Bill Number: {randomNumber}</span>
                <br />
                <span>Date: {billDate}</span>
              </td>
            </tr>
            <tr className="information">
              <td colSpan={3}>
                <table>
                  <tbody>
                    <tr>
                      <td colSpan={2}>
                        <b> Shipping Address: address shipping </b> <br />
                        admin <br />
                        09999999999
                        <br />
                        info@admin.com
                        <br />
                        www.admin.com
                      </td>
                      <td>
                        <b> Billing Address: from admin </b>
                        <br />
                        Admin.
                        <br />
                        Dev
                        <br />
                        admin@example.com
                      </td>
                    </tr>
                  </tbody>
                </table>
              </td>
            </tr>
            <tr>
              <td colSpan={3}>
                <table cellSpacing="0px" cellPadding="2px">
                  <tbody>
                    <tr className="heading">
                      <td style={{ width: "25%" }}>ITEM</td>
                      <td style={{ width: "10%", textAlign: "center" }}>
                        Size
                      </td>
                      <td style={{ width: "10%", textAlign: "right" }}>
                        Price
                      </td>
                      <td style={{ width: "15%", textAlign: "right" }}>
                        TOTAL AMOUNT
                      </td>
                    </tr>
                    {detailInvoice.map((item) => (
                      <tr className="item" key={item.id}>
                        <td style={{ width: "25%" }}>
                          <b> {item.idItem} </b>
                        </td>
                        <td style={{ width: "10%", textAlign: "center" }}>2</td>
                        <td style={{ width: "10%", textAlign: "right" }}>
                          {item.idItem}
                        </td>
                        <td style={{ width: "15%", textAlign: "right" }}>
                          {item.amount}
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </td>
            </tr>
            <tr>
              <td colSpan={3}>
                <table cellSpacing="0px" cellPadding="2px">
                  <tbody>
                    <tr>
                      <td width="50%">
                        <b> Declaration: </b> <br />
                        We declare that this invoice shows the actual price of
                        the goods described above and that all particulars are
                        true and correct. The goods sold are intended for end
                        user consumption and not for resale.
                      </td>
                      <td>
                        * This is a computer generated invoice and does not
                        require a physical signature
                      </td>
                    </tr>
                    <tr>
                      <td width="50%"></td>
                      <td>
                        <b> Authorized Signature </b>
                        <br />
                        <br />
                        ...................................
                        <br />
                        <br />
                        <br />
                      </td>
                    </tr>
                  </tbody>
                </table>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </Container>
  );
}

export default InvoiceDetail;
