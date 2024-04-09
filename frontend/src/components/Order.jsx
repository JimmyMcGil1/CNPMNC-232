
import { useState, useEffect } from 'react';
import Body from './Body/Body';
import api from '../api/api';


const Order = () => {
  const [orders, setOrders] = useState();
  const [fetchDone, setFetchDone] = useState(false);
  useEffect(() => {
    async function getOrders() {
      try {
        console.log('is called')
        setFetchDone(false);
        const getData = await api.get("/orders/all");
        setOrders(getData.data);
        console.log(getData)
        setFetchDone(true);

      } catch (error) {
        console.log("Fail to fetch data:" + error.message);
      }
    }
    getOrders();
  }, [])

  return (
    <div className='w-full h-full flex flex-col'>
      <Body />
      {
        fetchDone && (
          <OrderTable items={orders} />
        )
      }
    </div>
  );
}

function OrderTable({ items }) {
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
            </tr>
          </thead>
          <tbody className="text-gray-600 text-sm font-semibold ">
            {items.map((item, index) => (
              <tr key={index} className="border-b border-gray-200 hover:bg-cyan-100">
                <td className="py-3 px-6 text-left whitespace-nowrap">{item.id}</td>
                <td className="py-3 px-6 text-left">{item.orderDate}</td>
                <td className="py-3 px-6 text-left">{item.deposit}</td>
                <td className="py-3 px-6 text-left">{item.supplierName}</td>
                <td className="py-3 px-6 text-left">{item.totalCost}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
export default Order;
