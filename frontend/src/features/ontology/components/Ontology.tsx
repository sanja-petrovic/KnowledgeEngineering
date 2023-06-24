import Button from "@/common/components/button/Button";
import { Collapse, Divider, Form, Input, InputNumber, Select } from "antd";
import { useForm } from "antd/lib/form/Form";
import { useState } from "react";
import styles from "../styles/ontology.module.scss";
const Ontology = () => {
  const [componentForm] = useForm();
  const [recommendationForm] = useForm();
  const [selectedComponent, setSelectedComponent] = useState();
  const ramTypes = ["ddr", "ddr2", "ddr3", "ddr4", "ddr5"];

  const getComponentOptions = () => [
    {
      label: "CPU",
      value: "cpu",
    },
    {
      label: "RAM",
      value: "ram",
    },
    {
      label: "Motherboard",
      value: "motherboard",
    },
    {
      label: "GPU",
      value: "gpu",
    },
    {
      label: "Storage",
      value: "storage",
    },
    {
      label: "Power supply",
      value: "powersupply",
    },
  ];

  const getCpuInputFields = () => {
    return (
      <>
        <Form.Item name="clockSpeed">
          <InputNumber
            style={{ width: "500px" }}
            addonAfter="GHz"
            placeholder="Clock speed"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="coreCount">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Core count"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="manufacturer">
          <Input style={{ width: "500px" }} placeholder="Manufacturer"></Input>
        </Form.Item>
        <Form.Item name="priceMin">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Minimum price"
            addonAfter="€"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="priceMax">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Maximum price"
            addonAfter="€"
          ></InputNumber>
        </Form.Item>
      </>
    );
  };

  const getRamInputFields = () => {
    return (
      <>
        <Form.Item name="frequency">
          <InputNumber
            style={{ width: "500px" }}
            addonAfter="MHz"
            placeholder="Frequency"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="latency">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Latency"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="size">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Size"
            addonAfter="GB"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="type">
          <Select
            style={{ width: "500px" }}
            placeholder="Type"
            options={ramTypes.map((ramType) => ({
              label: ramType.toUpperCase(),
              value: ramType,
            }))}
          ></Select>
        </Form.Item>
        <Form.Item name="manufacturer">
          <Input style={{ width: "500px" }} placeholder="Manufacturer"></Input>
        </Form.Item>
        <Form.Item name="priceMin">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Minimum price"
            addonAfter="€"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="priceMax">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Maximum price"
            addonAfter="€"
          ></InputNumber>
        </Form.Item>
      </>
    );
  };

  const getGpuInputFields = () => {
    return (
      <>
        <Form.Item name="clockSpeedMax">
          <InputNumber
            style={{ width: "500px" }}
            addonAfter="MHz"
            placeholder="Maximum lock speed"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="clockSpeedMin">
          <InputNumber
            style={{ width: "500px" }}
            addonAfter="MHz"
            placeholder="Minimum lock speed"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="maxVRAM">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Maximum VRAM"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="minVRAM">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Minimum VRAM"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="manufacturer">
          <Input style={{ width: "500px" }} placeholder="Manufacturer"></Input>
        </Form.Item>
        <Form.Item name="priceMin">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Minimum price"
            addonAfter="€"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="priceMax">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Maximum price"
            addonAfter="€"
          ></InputNumber>
        </Form.Item>
      </>
    );
  };

  const getStorageInputFields = () => {
    return (
      <>
        <Form.Item name="type">
          <Input style={{ width: "500px" }} placeholder="Type"></Input>
        </Form.Item>
        <Form.Item name="writeSpeedMin">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Minimum write speed"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="memoryMax">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Maximum memory size"
            addonAfter="GB"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="memoryMin">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Minimum memory size"
            addonAfter="GB"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="manufacturer">
          <Input style={{ width: "500px" }} placeholder="Manufacturer"></Input>
        </Form.Item>
        <Form.Item name="priceMin">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Minimum price"
            addonAfter="€"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="priceMax">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Maximum price"
            addonAfter="€"
          ></InputNumber>
        </Form.Item>
      </>
    );
  };

  const getMotherboardInputFields = () => {
    return (
      <>
        <Form.Item name="type">
          <Input style={{ width: "500px" }} placeholder="Type"></Input>
        </Form.Item>
        <Form.Item name="minRAM">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Minimum RAM"
            addonAfter="GB"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="maxRAM">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Maximum RAM"
            addonAfter="GB"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="manufacturer">
          <Input style={{ width: "500px" }} placeholder="Manufacturer"></Input>
        </Form.Item>
        <Form.Item name="priceMin">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Minimum price"
            addonAfter="€"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="priceMax">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Maximum price"
            addonAfter="€"
          ></InputNumber>
        </Form.Item>
      </>
    );
  };

  const getPowerSupplyInputFields = () => {
    return (
      <>
        <Form.Item name="wattage">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Wattage"
            addonAfter="W"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="manufacturer">
          <Input style={{ width: "500px" }} placeholder="Manufacturer"></Input>
        </Form.Item>
        <Form.Item name="priceMin">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Minimum price"
            addonAfter="€"
          ></InputNumber>
        </Form.Item>
        <Form.Item name="priceMax">
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Maximum price"
            addonAfter="€"
          ></InputNumber>
        </Form.Item>
      </>
    );
  };

  const handleComponentFinish = () => {
    console.log(componentForm.getFieldsValue());
  };
  return (
    <div className={styles.wrapper}>
      <h1>Ontology</h1>
      <div>
        In information science, an ontology encompasses a representation, formal
        naming, and definition of the categories, properties, and relations
        between the concepts, data, and entities that substantiate one, many, or
        all domains of discourse. More simply, an ontology is a way of showing
        the properties of a subject area and how they are related, by defining a
        set of concepts and categories that represent the subject.
      </div>
      <Divider style={{ marginTop: "-1rem" }} />
      <Collapse size="large" defaultActiveKey={[-1]} ghost>
        <Collapse.Panel header={<h2>Component recommendation</h2>} key="1">
          Recommend a component based on desired properties.
          <Form
            form={componentForm}
            style={{ marginTop: "1rem" }}
            onFinish={handleComponentFinish}
          >
            <Form.Item
              name="component"
              rules={[
                {
                  required: true,
                  message: "Please select a component.",
                },
              ]}
            >
              <Select
                allowClear
                style={{ width: "500px" }}
                options={getComponentOptions()}
                onChange={(e) => {
                  setSelectedComponent(e);
                  console.log(e);
                }}
                placeholder="Select component type"
              ></Select>
            </Form.Item>
            {!!selectedComponent &&
              selectedComponent === "cpu" &&
              getCpuInputFields()}
            {!!selectedComponent &&
              selectedComponent === "ram" &&
              getRamInputFields()}
            {!!selectedComponent &&
              selectedComponent === "gpu" &&
              getGpuInputFields()}
            {!!selectedComponent &&
              selectedComponent === "motherboard" &&
              getMotherboardInputFields()}
            {!!selectedComponent &&
              selectedComponent === "storage" &&
              getStorageInputFields()}
            {!!selectedComponent &&
              selectedComponent === "powersupply" &&
              getPowerSupplyInputFields()}
            <Button
              type="primary"
              text="Submit"
              style={{ width: "500px" }}
            ></Button>
          </Form>
        </Collapse.Panel>
        <Collapse.Panel
          header={<h2>Upgrade recommendation</h2>}
          key="2"
        ></Collapse.Panel>
      </Collapse>
    </div>
  );
};

export default Ontology;
