import { DeleteOutlined, EditOutlined } from '@ant-design/icons';
import { Button, Form, Input, InputNumber, Modal, Table } from 'antd';
import React, { useState } from 'react';

const { Column } = Table;

const OrderForm = () => {
  const [items, setItems] = useState([]);
  const [item, setItem] = useState('');
  const [price, setPrice] = useState(0);
  const [amount, setAmount] = useState(0);
  const [form] = Form.useForm();
  const [editingIndex, setEditingIndex] = useState(-1);
  const [isModalVisible, setIsModalVisible] = useState(false);

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
    if (editingIndex === -1) {
      CreateItem();
    } else {
      UpdateItem();
    }
  };

  const CreateItem = () => {
    setItems([...items, { item, price, amount }]);
    form.resetFields(); // Reset form fields after submission
    setItem(''); // Reset item state
    setPrice(0); // Reset price state
    setAmount(0); // Reset amount state
  };

  const UpdateItem = () => {
    const updatedItems = [...items];
    updatedItems[editingIndex] = { item, price, amount };
    setItems(updatedItems);
    form.resetFields(); // Reset form fields after submission
    setItem(''); // Reset item state
    setPrice(0); // Reset price state
    setAmount(0); // Reset amount state
    setEditingIndex(-1); // Reset editing index
    setIsModalVisible(false); // Close modal
  };

  const handleEdit = (index) => {
    setEditingIndex(index);
    const { item, price, amount } = items[index];
    setItem(item);
    setPrice(price);
    setAmount(amount);
    setIsModalVisible(true);
  };

  const handleDelete = (index) => {
    const newItems = items.filter((_, i) => i !== index);
    setItems(newItems);
  };

  return (
    <>
      <Form
        form={form}
        onFinish={onFinish} // Call onFinish when form is submitted
        className='w-full flex mt-5'
        name='basic'
      >
        <Form.Item label='Item' name='item' className='ml-2'
          rules={[{ required: true, message: 'Please input Item!' }]}
        >
          <Input value={item} onChange={(e) => onChangeItem(e.target.value)} />
        </Form.Item>
        <Form.Item label='Price' name='price' className='ml-2'
          rules={[{ required: true, message: 'Please input Price!' }]}
        >
          <InputNumber min={0} defaultValue={0} value={price} onChange={onChangePrice} />
        </Form.Item>
        <Form.Item label='Amount' name='amount' className='ml-2'
          rules={[{ required: true, message: 'Please input Amount!' }]}
        >
          <InputNumber min={0} defaultValue={0} value={amount} onChange={onChangeAmount} />
        </Form.Item>
        <Form.Item className='ml-2'>
          <Button type='primary' htmlType='submit'>
            Add Item
          </Button>
        </Form.Item>
      </Form>
      <Table dataSource={items}>
        <Column title="ITEM ID" dataIndex='idItem' key='idItem' render={(text, record, index) => index + 1}/>
        <Column title="Item" dataIndex='item' key='item'/>
        <Column title="Price" dataIndex='price' key='price'/>
        <Column title="Amount" dataIndex='amount' key='amount'/>
        <Column
          title="Edit"
          key="edit"
          render={(_, record, index) => (
            <Button icon={<EditOutlined />} onClick={() => handleEdit(index)} />
          )}
        />
        <Column
          title="Delete"
          key="delete"
          render={(_, record, index) => (
            <Button icon={<DeleteOutlined />} onClick={() => handleDelete(index)} />
          )}
        />
      </Table>
      <Modal
        title="Edit Item"
        visible={isModalVisible}
        onCancel={() => setIsModalVisible(false)}
        footer={[
          <Button key="cancel" onClick={() => setIsModalVisible(false)}>
            Cancel
          </Button>,
          <Button key="submit" type="primary" onClick={onFinish}>
            Save
          </Button>,
        ]}
      >
        <Form
          form={form}
          onFinish={onFinish}
          className='w-full flex mt-5'
          name='basic'
        >
          <Form.Item label='Item' name='item'>
            <Input value={item} defaultValue={item} onChange={(e) => onChangeItem(e.target.value)} />
          </Form.Item>
          <Form.Item label='Price' name='price'>
            <InputNumber min={0} defaultValue={price} onChange={onChangePrice} />
          </Form.Item>
          <Form.Item label='Amount' name='amount'>
            <InputNumber min={0} defaultValue={amount} onChange={onChangeAmount} />
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
};

export default OrderForm;
