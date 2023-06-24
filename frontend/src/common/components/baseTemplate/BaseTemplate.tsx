import {
  ApartmentOutlined,
  BarChartOutlined,
  BlockOutlined,
  DeploymentUnitOutlined,
  ReadOutlined,
  ShareAltOutlined,
} from "@ant-design/icons";
import type { MenuProps } from "antd";
import { Layout, Menu, Typography } from "antd";
import { useRouter } from "next/router";
import { ReactNode } from "react";

const { Text, Link } = Typography;

const { Header, Footer, Sider, Content } = Layout;
type MenuItem = Required<MenuProps>["items"][number];

function getItem(
  label: React.ReactNode,
  key: React.Key,
  icon?: React.ReactNode,
  children?: MenuItem[]
): MenuItem {
  return {
    key,
    icon,
    children,
    label,
  } as MenuItem;
}
interface BaseTemplateProps {
  children?: ReactNode;
}

import styles from "./BaseTemplate.module.scss";

const BaseTemplate = ({ children }: BaseTemplateProps) => {
  const router = useRouter();
  const items: MenuItem[] = [
    getItem("Introduction", "/", <ReadOutlined />),
    { type: "divider" },
    getItem("Ontology", "ontology", <ApartmentOutlined />),
    getItem("Fuzzy systems", "fuzzy-systems", <BarChartOutlined />),
    getItem("Bayesian network", "bayesian-network", <DeploymentUnitOutlined />),
    getItem("Case-based reasoning", "case-based-reasoning", <BlockOutlined />),
  ];

  const onClick: MenuProps["onClick"] = (e) => {
    router.push(e.key);
  };
  return (
    <Layout style={{ minHeight: "100vh" }}>
      <Sider theme="light" width="300px">
        <div className={styles.siderHeader}>
          <ShareAltOutlined style={{ color: "white" }} />
          <h1>Knowledge Engineering</h1>
        </div>
        <Menu
          onClick={onClick}
          items={items}
          defaultSelectedKeys={[router.pathname.substring(1)]}
        ></Menu>
      </Sider>
      <Content style={{ backgroundColor: "#fafaff" }}>{children}</Content>
    </Layout>
  );
};

export default BaseTemplate;
