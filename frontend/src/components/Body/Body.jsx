import { PlusOutlined } from '@ant-design/icons';
import { Button } from 'antd';
import OrderForm from '../OderForm/OderForm';

const Body = () => {
    return (
        <div className='flex flex-col ml-auto mr-auto w-2/3'>
            <div className='flex justify-end w-full mb-2'>
                <Button className='mt-5'>
                    <div className='flex item-center'>
                        <PlusOutlined className='mr-2'/>
                        <span>Create Order</span>
                    </div>
                </Button>
            </div>
            <OrderForm/>
      </div>
    )
}

export default Body