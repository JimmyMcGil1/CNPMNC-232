import { Button, Form, Input, Table } from 'antd';
import { useState } from 'react';

const { Column, ColumnGroup } = Table;
const columns = [
    {title: "ID", index: 'id', key: 'id'},
    {title:"Item", index: 'item', key: 'item'},
    {title: 'Price', index:'price', key: 'price'},
    {title: 'amount', index: 'amount', key:'amount'}
]
const Order = () => {
    const [isForm,setIsForm] = useState(false)
    const [id, setId] = useState('')
    const [item, setItem] = useState('')
    const [price, setPrice] = useState('')
    const [amount, setAmount] = useState('')
    const [dataList, setDataList] = useState([])
    const handleAddItem = () => {
        setIsForm(true)
    }
    const handleChangeId = (e) => {
        setId(e.target.value)
    }
    const handleChangeItem= (e) => {
        setItem(e.target.value)
    }
    const handleChangePrice = (e) => {
        setPrice(e.target.value)
    }
    const handleChangeAmount = (e) => { 
        setAmount(e.target.value)
    }
    const handleSubmit = () =>{
        const input = {id, item, price, amount}
        setDataList([...dataList,input])
        setIsForm(false)
    }
    return (
        <div>
            <header>Order</header>
            <Button onClick={handleAddItem}>Add Item</Button>
            <Table dataSource={dataList}>
                {columns.map(column => {
                    return (<Column title={column.title} dataIndex={column.index} key={column.key} />)
                })}
                
            </Table>
            {isForm && 
            <Form
            name="basic"   
          >
            <Form.Item
              label="ID"
              name="id"
              rules={[
                {
                  required: true,
                  message: 'Please input ID',
                },
              ]}
            >
              <Input value={id} onChange={handleChangeId}/>
            {console.log(id)}
            </Form.Item>
        
            <Form.Item
              label="Item"
              name="item"
              rules={[
                {
                  required: true,
                  message: 'Please input Item',
                },
              ]}
            >
              <Input value={item} onChange={handleChangeItem}/>
            </Form.Item>
            <Form.Item
              label="Price"
              name="price"
              rules={[
                {
                  required: true,
                  message: 'Please input Price',
                },
              ]}
            >
              <Input value={price} onChange={handleChangePrice}/>
            </Form.Item>
            <Form.Item
              label="Amount"
              name="amount"
              rules={[
                {
                  required: true,
                  message: 'Please input Amount',
                },
              ]}
            >
              <Input value={amount} onChange={handleChangeAmount}/>
            </Form.Item>
            <Form.Item
            >
              <Button onClick={handleSubmit}>
                Submit
              </Button>
            </Form.Item>
          </Form>}
        </div>
    )
}
export default Order