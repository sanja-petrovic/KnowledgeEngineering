import {
  DeploymentUnitOutlined,
  DesktopOutlined,
  FileOutlined,
  PieChartOutlined,
  UserOutlined,
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
    getItem("Ontology", "ontology", <PieChartOutlined />),
    getItem("Fuzzy systems", "fuzzy-systems", <DesktopOutlined />),
    getItem("Bayesian network", "bayesian-network", <UserOutlined />),
    getItem("Case-based reasoning", "case-based-reasoning", <FileOutlined />),
  ];

  const onClick: MenuProps["onClick"] = (e) => {
    router.push(e.key);
  };
  return (
    <Layout style={{ minHeight: "100vh" }}>
      <Sider theme="light" width="300px">
        <div className={styles.siderHeader}>
          <DeploymentUnitOutlined style={{ color: "white" }} />
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
