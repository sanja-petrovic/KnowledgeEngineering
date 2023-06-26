import Button from "@/common/components/button/Button";
import { CbrResult } from "@/features/types/CbrResult";
import { ComputerDescription } from "@/features/types/ComputerDescription";
import {
  Descriptions,
  Divider,
  Drawer,
  Form,
  Input,
  InputNumber,
  Select,
} from "antd";
import { useForm } from "antd/lib/form/Form";
import { useEffect, useState } from "react";
import {
  getAllDescriptions,
  getSimilar,
  getSimilarV2,
} from "../services/cbr.service";
import styles from "../styles/cbr.module.scss";

const CaseBasedReasoning = () => {
  const [computers, setComputers] = useState<ComputerDescription[]>([]);
  const [similar, setSimilar] = useState<CbrResult[]>([]);
  const [form] = useForm();
  const [open, setOpen] = useState(false);
  const [selected, setSelected] = useState<CbrResult | null>(null);
  const [componentsForm] = useForm();
  const ramTypes = ["DDR", "DDR2", "DDR3", "DDR4", "DDR5"];
  const [selectedComputer, setSelectedComputer] = useState<string>("");

  const showDrawer = (result: CbrResult) => {
    setOpen(true);
    setSelected(result);
  };

  const onClose = () => {
    setOpen(false);
    setSelected(null);
  };

  useEffect(() => {
    getAllDescriptions()
      .then((response) => setComputers(response.data))
      .catch((error) => console.log(error));
  }, []);

  const formatName = (name: string) => {
    return name
      .replaceAll("-", " ")
      .toLowerCase()
      .split(" ")
      .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
      .join(" ");
  };

  const handleEvaluate = () => {
    getSimilar(form.getFieldValue("computer"))
      .then((response) => {
        setSimilar(response.data.results);
        setSelectedComputer(form.getFieldValue("computer"));
      })
      .catch((error) => console.log(error));
  };

  const handleEvaluateComponents = async () => {
    await getSimilarV2(componentsForm.getFieldsValue() as ComputerDescription)
      .then((response) => {
        setSimilar(response.data.results);
        setSelectedComputer(componentsForm.getFieldValue("name"));
      })
      .catch((error) => console.log(error));
  };

  return (
    <div className={styles.wrapper}>
      <h1>Case-based reasoning</h1>
      <div>
        Case-based reasoning (CBR), broadly construed, is the process of solving
        new problems based on the solutions of similar past problems. It has
        been formalized as a four-step process:
        <ul>
          <li>
            <b>Retrieve</b>: Given a target problem, retrieve cases relevant to
            solving it from memory.
          </li>
          <li>
            <b>Reuse</b>: Map the solution from the previous case to the target
            problem.
          </li>
          <li>
            <b>Revise</b>: Having mapped the previous solution to the target
            situation, test the new solution in the real world (or a simulation)
            and, if necessary, revise.
          </li>
          <li>
            <b>Retain</b>: After the solution has been successfully adapted to
            the target problem, store the resulting experience as a new case in
            memory.
          </li>
        </ul>
      </div>
      <Divider style={{ marginTop: "-1rem" }} />
      <h2>Similarity evaluation</h2>
      <div style={{ marginTop: "-1rem" }}>
        <div style={{ maxWidth: "500px" }}>
          Select a computer from the list below to get the top 5 computers most
          similar to it.
        </div>
      </div>
      <Form form={form} onFinish={handleEvaluate}>
        <Form.Item
          rules={[{ required: true, message: "Please select a computer." }]}
          name="computer"
        >
          <Select
            style={{ width: "500px" }}
            allowClear
            placeholder="Select a computer"
            options={computers.map((item) => ({
              label: formatName(item.name),
              value: item.name,
            }))}
          ></Select>
        </Form.Item>
        <Button type="primary" style={{ width: "500px" }} text="Evaluate" />
      </Form>
      <Divider
        style={{
          width: "500px",
          minWidth: "500px",
        }}
      >
        Or
      </Divider>
      <div style={{ maxWidth: "500px" }}>
        Enter the properties of a computer below to get the top 5 computers most
        similar to it.
      </div>
      <Form
        form={componentsForm}
        onFinish={handleEvaluateComponents}
        className={styles.secondForm}
      >
        <Form.Item
          name="name"
          rules={[{ required: true, message: "This field is required." }]}
        >
          <Input style={{ width: "500px" }} placeholder="Computer name" />
        </Form.Item>
        <Form.Item
          name="manufacturer"
          rules={[{ required: true, message: "This field is required." }]}
        >
          <Input style={{ width: "500px" }} placeholder="Manufacturer" />
        </Form.Item>
        <Form.Item
          name="releaseYear"
          rules={[{ required: true, message: "This field is required." }]}
        >
          <InputNumber style={{ width: "500px" }} placeholder="Release year" />
        </Form.Item>
        <Form.Item
          name="cpuSpeedGhz"
          rules={[{ required: true, message: "This field is required." }]}
        >
          <InputNumber
            style={{ width: "500px" }}
            placeholder="CPU speed"
            addonAfter="GHz"
          />
        </Form.Item>
        <Form.Item
          name="cpuCores"
          rules={[{ required: true, message: "This field is required." }]}
        >
          <InputNumber style={{ width: "500px" }} placeholder="CPU cores" />
        </Form.Item>
        <Form.Item
          name="gpuSpeedMhz"
          rules={[{ required: true, message: "This field is required." }]}
        >
          <InputNumber
            style={{ width: "500px" }}
            placeholder="GPU speed"
            addonAfter="MHz"
          />
        </Form.Item>
        <Form.Item
          name="ramType"
          rules={[{ required: true, message: "This field is required." }]}
        >
          <Select
            style={{ width: "500px" }}
            options={ramTypes.map((ramType) => ({
              label: ramType,
              value: ramType,
            }))}
            placeholder="RAM type"
          />
        </Form.Item>
        <Form.Item
          name="ramSizeGb"
          rules={[{ required: true, message: "This field is required." }]}
        >
          <InputNumber
            style={{ width: "500px" }}
            placeholder="RAM size"
            addonAfter="GB"
          />
        </Form.Item>
        <Form.Item
          name="ramSpeedMhz"
          rules={[{ required: true, message: "This field is required." }]}
        >
          <InputNumber
            style={{ width: "500px" }}
            placeholder="RAM speed"
            addonAfter="MHz"
          />
        </Form.Item>
        <Form.Item
          name="storageGb"
          rules={[{ required: true, message: "This field is required." }]}
        >
          <InputNumber
            style={{ width: "500px" }}
            placeholder="Storage capacity"
            addonAfter="GB"
          />
        </Form.Item>

        <Button type="primary" style={{ width: "500px" }} text="Evaluate" />
      </Form>
      {similar.length > 0 && (
        <>
          <Divider
            style={{ marginTop: "-1rem" }}
            orientation="left"
            orientationMargin={0}
          >
            Results
          </Divider>
          <Descriptions
            title={`Top 5 most similar computers to ${formatName(
              selectedComputer
            )}`}
          >
            {similar.map((result) => (
              <Descriptions.Item
                span={3}
                key={result.description.name}
                label={
                  <div
                    style={{ cursor: "pointer" }}
                    onClick={() => showDrawer(result)}
                  >
                    {formatName(result.description.name)}
                  </div>
                }
              >
                {(result.evaluation * 100).toFixed(4)}%
              </Descriptions.Item>
            ))}
          </Descriptions>
          <Drawer
            width={480}
            placement="right"
            closable={false}
            onClose={onClose}
            style={{ padding: "1rem" }}
            open={open}
          >
            {!!selected && (
              <>
                <Descriptions
                  title={
                    <h2 style={{ margin: 0 }}>
                      {formatName(selected?.description.name)}
                    </h2>
                  }
                >
                  <Descriptions.Item
                    span={3}
                    style={{ textTransform: "capitalize" }}
                    label="Manufacturer"
                  >
                    {selected.description.manufacturer}
                  </Descriptions.Item>
                  <Descriptions.Item span={3} label="Release year">
                    {selected.description.releaseYear}
                  </Descriptions.Item>
                  <Descriptions.Item span={3} label="CPU speed">
                    {selected.description.cpuSpeedGhz} GHz
                  </Descriptions.Item>
                  <Descriptions.Item span={3} label="CPU cores">
                    {selected.description.cpuCores}
                  </Descriptions.Item>
                  <Descriptions.Item span={3} label="GPU speed">
                    {selected.description.gpuSpeedMhz} MHz
                  </Descriptions.Item>
                  <Descriptions.Item span={3} label="RAM type">
                    {selected.description.ramType.toUpperCase()}
                  </Descriptions.Item>
                  <Descriptions.Item span={3} label="RAM size">
                    {selected.description.ramSizeGb} GB
                  </Descriptions.Item>
                  <Descriptions.Item span={3} label="RAM speed">
                    {selected.description.ramSpeedMhz} MHz
                  </Descriptions.Item>
                  <Descriptions.Item span={3} label="Storage capacity">
                    {selected.description.storageGb} GB
                  </Descriptions.Item>
                </Descriptions>
              </>
            )}
          </Drawer>
        </>
      )}
    </div>
  );
};

export default CaseBasedReasoning;
