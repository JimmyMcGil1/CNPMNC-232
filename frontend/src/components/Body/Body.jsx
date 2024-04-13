import { PlusOutlined } from "@ant-design/icons";
import { Button, Modal } from "antd";
import { useState } from "react";
import OrderForm from "../OderForm/OderForm";
import { useNavigate } from "react-router-dom";

const Body = () => {
  const [isOrderFormVisible, setIsOrderFormVisible] = useState(false);
  const router = useNavigate();
  const showOrderForm = () => {
    setIsOrderFormVisible(true);
  };

  const hideOrderForm = () => {
    setIsOrderFormVisible(false);
  };

  const handleViewProduct = () => {
    router("/product/list");
  };

  return (
    <div className="flex flex-col ml-auto mr-auto w-2/3">
      <div className="flex justify-end w-full mb-2">
        <Button className="mt-5" onClick={showOrderForm}>
          <div className="flex item-center">
            <PlusOutlined className="mr-2" />
            <span>Create Order</span>
          </div>
        </Button>
        <Button className="mt-5 ml-2" onClick={handleViewProduct}>
          <div className="flex item-center">
            <span> View List Product</span>
          </div>
        </Button>
      </div>
      <Modal
        title="Create Order"
        visible={isOrderFormVisible}
        onCancel={hideOrderForm}
        footer={null}
        width={800}
      >
        <OrderForm onCancel={hideOrderForm} />
      </Modal>
    </div>
  );
};

export default Body;
