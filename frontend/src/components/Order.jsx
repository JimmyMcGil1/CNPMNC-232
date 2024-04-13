import React, { useState, useEffect } from "react";
import Body from "./Body/Body";
import api from "../api/api";
import { Modal, Table } from "antd";
import OrderForm from "./OderForm/OderForm";

const Order = () => {
  const [orders, setOrders] = useState();
  const [fetchDone, setFetchDone] = useState(false);
  const [orderDtModal, setOrderDtModal] = useState(false);
  const [crOrDt, setCrOrDt] = useState(null);
  const [crOrDtId, setCrOrDtId] = useState(0);
  useEffect(() => {
    async function getOrders() {
      try {
        setFetchDone(false);
        const getData = await api.get("/orders/all");
        setOrders(getData.data);
        setFetchDone(true);
      } catch (error) {
        console.log("Fail to fetch data:" + error.message);
      }
    }
    getOrders();
  }, []);

  return (
    <div className="w-full h-full flex flex-col">
      <Modal
        title={"Order " + crOrDtId}
        open={orderDtModal}
        footer={null}
        onCancel={() => setOrderDtModal(false)}
      >
        <Table dataSource={crOrDt} columns={itemsColums} />
      </Modal>
      <Body />
      {fetchDone && (
        <OrderTable items={orders} handleChooseOrder={handleChooseOrder} />
      )}
    </div>
  );

  async function handleChooseOrder(id) {
    try {
      const getOrDtResp = await api.get("/order-item/all/items/" + id);
      setCrOrDt(getOrDtResp.data);
      setCrOrDtId(id);
      setOrderDtModal(true);
    } catch (e) {
      console.log("fail to fetch order detail: " + e.message);
    }
  }
};

function OrderTable({ items, handleChooseOrder }) {
  var url = '/invoice-detail/';
  return (
    <div className="container mx-auto ">
      <div className="bg-white shadow-md rounded my-6">
        <table className="w-full table-auto rounded-xl">
          <thead>
            <tr className="bg-gray-200 text-gray-600 uppercase text-sm leading-normal">
              <th className="py-3 px-6 text-left">Order ID</th>
              <th className="py-3 px-6 text-left">Date Order</th>
              <th className="py-3 px-6 text-left">Deposit</th>
              <th className="py-3 px-6 text-left">Supplier Name</th>
              <th className="py-3 px-6 text-left">Total Cost</th>
              <th className="py-3 px-6 text-left">Invoice</th>
            </tr>
          </thead>
          <tbody className="text-gray-600 text-sm font-semibold ">
            {items.map((item, index) => (
              <tr
                key={index}
                className="border-b border-gray-200 hover:bg-cyan-100"
                onClick={() => handleChooseOrder(item.id)}
              >
                <td className="py-3 px-6 text-left whitespace-nowrap">
                  {item.id}
                </td>
                <td className="py-3 px-6 text-left">{item.orderDate}</td>
                <td className="py-3 px-6 text-left">{item.deposit}</td>
                <td className="py-3 px-6 text-left">{item.supplierName}</td>
                <td className="py-3 px-6 text-left">{item.totalCost}</td>
                <td className="py-3 px-6 text-left"><a href={url + item.id}>Invoice</a></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

const itemsColums = [
  {
    title: "ITEM ID",
    dataIndex: "idItem",
    key: "idItem",
  },
  {
    title: "Price",
    dataIndex: "salePrice",
    key: "salePrice",
  },
  {
    title: "Amount",
    dataIndex: "amount",
    key: "amount",
  },
];

export default Order;
