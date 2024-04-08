import { Button, Form, Input, InputNumber, Table } from 'antd';
import React, { useState } from 'react';

const columns = [
  {
    title: "STT",
    dataIndex:"counter",
    key:'counter',
  },
  {
    title: "Item",
    dataIndex:"item",
    key:'item',
  },
  {
    title: "Price",
    dataIndex:"price",
    key:'price',
  },
  {
    title: "Amount",
    dataIndex:"amount",
    key:'amount',
  }
]



const OrderForm = () => {
  const [item, setItem] = useState('');
  const [price, setPrice] = useState(0);
  const [amount, setAmount] = useState(0);
  const [items, setItems] = useState([]);
  const [form] = Form.useForm();
  const [counter, setCounter] = useState(1)

  const onChangeItem = (value) => {
    setItem(value);
  };

  const onChangePrice = (value) => {
    setPrice(value);
  };

  const onChangeAmount = (value) => {
    setAmount(value);
  };

  const onFinish = () => {
    CreateItem(); // Call CreateItem from onFinish
  };

  const CreateItem = () => {
    setItems([...items, { counter, item, price, amount }]);
    form.resetFields(); // Reset form fields after submission
    setItem(''); // Reset item state
    setPrice(0); // Reset price state
    setAmount(0); // Reset amount state
    setCounter(counter+1)
  };

  return (
    <>
    <Form
      form={form}
      onFinish={onFinish} // Call onFinish when form is submitted
      className='w-full flex mt-5'
      name='basic'
    >
      <Form.Item label='Item' name='item' className='ml-2'>
        <Input value={item} onChange={(e) => onChangeItem(e.target.value)} />
      </Form.Item>
      <Form.Item label='Price' name='price' className='ml-2'>
        <InputNumber min={0} defaultValue={price} onChange={onChangePrice} />
      </Form.Item>
      <Form.Item label='Amount' name='amount' className='ml-2'>
        <InputNumber min={0} defaultValue={0} value={amount} onChange={onChangeAmount} />
      </Form.Item>
      <Form.Item className='ml-2'>
        <Button type='primary' htmlType='submit'>
          Add Item
        </Button>
      </Form.Item>
    </Form>
    <Table columns={columns} dataSource={items} />
    </>
  );
};

export default OrderForm;
