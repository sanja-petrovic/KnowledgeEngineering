import Button from "@/common/components/button/Button";
import {
  Collapse,
  Divider,
  Form,
  Input,
  InputNumber,
  Select,
  Tag,
  Tooltip,
} from "antd";
import { useForm } from "antd/lib/form/Form";
import { useEffect, useState } from "react";
import {
  getComponentRecommendation,
  getDesktops,
  getUpgrades,
} from "../services/ontology.service";
import styles from "../styles/ontology.module.scss";
import { Parameters } from "../types/ComponentParameters";
import { Desktop } from "../types/Desktop";
const Ontology = () => {
  const [componentForm] = useForm();
  const [upgradeForm] = useForm();
  const [selectedComponent, setSelectedComponent] = useState();
  const ramTypes = ["DDR", "DDR2", "DDR3", "DDR4", "DDR5"];
  const [componentRecomendations, setComponentRecommendations] = useState<
    string[]
  >([]);
  const [selectedComponentForUpgrade, setSelectedComponentForUpgrade] =
    useState();
  const [desktops, setDesktops] = useState<Desktop[]>([]);
  const [upgrades, setUpgrades] = useState<string[]>([]);

  useEffect(() => {
    getDesktops()
      .then((response) => setDesktops(response.data))
      .catch((error) => console.log(error));
  }, []);

  const formatDesktop = (desktopName: string) =>
    desktopName.replaceAll("_", " ");

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

  const getComponentOptionsUpgrade = () => [
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
      label: "Chipset",
      value: "chipset",
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
              label: ramType,
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

  const handleComponentFinish = async () => {
    await getComponentRecommendation(
      selectedComponent ?? "",
      componentForm.getFieldsValue() as Parameters
    )
      .then((response) => {
        console.log(response.data.length);
        if (response.data.length === 0) {
          setComponentRecommendations(["-1"]);
        } else {
          setComponentRecommendations(response.data);
        }
      })
      .catch((error) => console.log(error));
  };

  const handleUpgradeFinish = async () => {
    getUpgrades(
      upgradeForm.getFieldValue("desktop"),
      upgradeForm.getFieldValue("componentType")
    )
      .then((response) => {
        if (response.data.length === 0) {
          setUpgrades(["-1"]);
        } else {
          setUpgrades(response.data);
        }
      })
      .catch((error) => console.log(error));
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
          {componentRecomendations.length > 0 && (
            <>
              <Divider orientation="left" orientationMargin={0}>
                Results
              </Divider>
              {componentRecomendations
                .filter((item) => item != "Evo" && item != "p")
                .map((result) =>
                  result === "-1" ? (
                    <>
                      The system did not find a suitable component to recommend.
                    </>
                  ) : (
                    <Tag color="#90b3ff" key={result}>
                      {result}
                    </Tag>
                  )
                )}
            </>
          )}
        </Collapse.Panel>
        <Collapse.Panel header={<h2>Upgrade recommendation</h2>} key="2">
          <Form form={upgradeForm} onFinish={handleUpgradeFinish}>
            <Form.Item name="componentType">
              <Select
                allowClear
                style={{ width: "500px" }}
                options={getComponentOptionsUpgrade()}
                placeholder="Select component type"
              ></Select>
            </Form.Item>
            <Form.Item name="desktop">
              <Select
                allowClear
                style={{ width: "500px" }}
                placeholder="Select desktop"
              >
                {desktops?.map((desktop) => (
                  <Select.Option value={desktop.name} key={desktop.name}>
                    <Tooltip
                      placement="left"
                      title={
                        <>
                          <b>{formatDesktop(desktop.name)}</b> <br />{" "}
                          Motherboard: {desktop.motherboard.name}
                          <br />
                          RAM: {desktop.motherboard.ram}
                          <br />
                          GPU: {desktop.gpu.name}
                          <br />
                          CPU: {desktop.motherboard.cpu.name}
                          <br />
                          Chipset: {desktop.motherboard.chipset.name}
                        </>
                      }
                    >
                      {formatDesktop(desktop.name)}
                    </Tooltip>
                  </Select.Option>
                ))}
              </Select>
            </Form.Item>
            <Button
              type="primary"
              text="Submit"
              style={{ width: "500px" }}
            ></Button>
          </Form>
          {upgrades.length > 0 && (
            <>
              <Divider orientation="left" orientationMargin={0}>
                Results
              </Divider>
              {upgrades.map((result) =>
                result === "-1" ? (
                  <>No upgrades found for this desktop and component.</>
                ) : (
                  <Tag color="#90b3ff" key={result}>
                    {result}
                  </Tag>
                )
              )}
            </>
          )}
        </Collapse.Panel>
      </Collapse>
    </div>
  );
};

export default Ontology;
