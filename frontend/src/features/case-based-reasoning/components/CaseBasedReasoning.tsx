import Button from "@/common/components/button/Button";
import { CbrResult } from "@/features/types/CbrResult";
import { ComputerDescription } from "@/features/types/ComputerDescription";
import { Descriptions, Divider, Form, Select } from "antd";
import { useForm } from "antd/lib/form/Form";
import { useEffect, useState } from "react";
import { getAllDescriptions, getSimilar } from "../services/cbr.service";
import styles from "../styles/cbr.module.scss";

const CaseBasedReasoning = () => {
  const [computers, setComputers] = useState<ComputerDescription[]>([]);
  const [similar, setSimilar] = useState<CbrResult[]>([]);
  const [form] = useForm();

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
    console.log(form.getFieldsValue());
    getSimilar(form.getFieldValue("computer"))
      .then((response) => setSimilar(response.data.results))
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
        Select a computer from the list below to get the top 5 computers most
        similar to it.
      </div>
      <Form form={form} className={styles.form} onFinish={handleEvaluate}>
        <Form.Item
          rules={[{ required: true, message: "Please select a computer." }]}
          name="computer"
        >
          <Select
            style={{ width: "300px" }}
            allowClear
            placeholder="Select a computer"
            options={computers.map((item) => ({
              label: formatName(item.name),
              value: item.name,
            }))}
          ></Select>
        </Form.Item>
        <Button type="primary" text="Evaluate" />
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
              form.getFieldValue("computer")
            )}`}
          >
            {similar.map((result) => (
              <Descriptions.Item
                span={3}
                key={result.description.name}
                label={formatName(result.description.name)}
              >
                {(result.evaluation * 100).toFixed(4)}%
              </Descriptions.Item>
            ))}
          </Descriptions>
        </>
      )}
    </div>
  );
};

export default CaseBasedReasoning;
