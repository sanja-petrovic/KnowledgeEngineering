import Button from "@/common/components/button/Button";
import { Divider, Form, Select } from "antd";
import { useForm } from "antd/lib/form/Form";
import { useEffect, useState } from "react";
import { getSymptoms } from "../services/bayes.service";
import styles from "../styles/bayes.module.scss";

const BayesianNetwork = () => {
  const [form] = useForm();
  const [symptoms, setSymptoms] = useState<string[]>();

  useEffect(() => {
    getSymptoms()
      .then((response) => setSymptoms(response.data))
      .catch((error) => console.log(error));
  }, []);

  const formatSymptom = (symptom: string) => {
    return symptom.replaceAll("_", " ");
  };

  return (
    <div className={styles.wrapper}>
      <h1>Bayesian network</h1>
      <div>
        A Bayesian network (also known as a Bayes network, Bayes net, belief
        network, or decision network) is a probabilistic graphical model that
        represents a set of variables and their conditional dependencies via a
        directed acyclic graph (DAG). Bayesian networks are ideal for taking an
        event that occurred and predicting the likelihood that any one of
        several possible known causes was the contributing factor.
      </div>
      <Divider style={{ marginTop: "-1rem" }} />
      <h2>Evaluate causes of malfunction</h2>
      <div style={{ marginTop: "-1rem" }}>
        Select symptoms from the list below to get the most likely causes behind
        them.
        <Form form={form} className={styles.form}>
          <Form.Item
            rules={[
              {
                required: true,
                message: "Please select at least one symptom.",
              },
            ]}
            name="symptoms"
          >
            <Select
              style={{ width: "700px" }}
              mode="multiple"
              allowClear
              placeholder="Select symptoms"
              options={symptoms?.map((symptom) => ({
                label: formatSymptom(symptom),
                value: symptom,
              }))}
            />
          </Form.Item>
          <Button type="primary" text="Evaluate" />
        </Form>
      </div>
    </div>
  );
};

export default BayesianNetwork;
