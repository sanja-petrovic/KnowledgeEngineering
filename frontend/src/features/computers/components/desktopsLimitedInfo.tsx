import { Card, Col, Row } from "antd";
import DesktopDto from "../types/desktopDto";
import { useEffect, useState } from "react";

const DesktopsLimitedInfo = () => {
  const [desktops, setDesktops] = useState<DesktopDto[]>([]);


  useEffect(() => {
    const fetchDesktops = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/ontology/desktop");
        const data = await response.json();
        setDesktops(data);
      } catch (error) {
        console.error('Error fetching desktops:', error);
      }
    };

    fetchDesktops();
  }, []);
  //add a function call that gets all accommodations  by host


  return (
    <Row gutter={16}>
        {desktops.map((desktop, index) => (
          <Col span={8} key={index}>
            <Card title={desktop.name} style={{ marginBottom: 16 }}>
              <p>Manufacturer: {desktop.manufacturer}</p>
              <p>Price: ${desktop.price}</p>
              <p>Motherboard: {desktop.motherboard.name}</p>
              <p>GPU: {desktop.gpu.name}</p>
            </Card>
          </Col>
        ))}
      </Row>
  );
};
export default DesktopsLimitedInfo;