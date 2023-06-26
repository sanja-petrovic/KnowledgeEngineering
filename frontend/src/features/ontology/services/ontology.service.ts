import api from "@/common/utils/axiosInstance";
import { Parameters } from "../types/ComponentParameters";

const prefix = "/ontology";

export const getComponentRecommendation = async (
  component: string,
  parameters: Parameters
) => {
  // @ts-ignore
  const params = new URLSearchParams(parameters);
  const filteredParams = {};
  params.forEach((value, key) => {
    if (
      value !== "undefined" &&
      value !== "null" &&
      value !== "" &&
      key !== "component"
    ) {
      //@ts-ignore
      filteredParams[key] = value;
    }
  });
  const path = `${prefix}/${component}/recommend?${new URLSearchParams(
    filteredParams
  ).toString()}`;
  return await api.get(path);
};

export const getDesktops = async () => await api.get(`${prefix}/desktop`);

export const getUpgrades = async (desktop: string, componentType: string) =>
  await api.get(
    `${prefix}/upgrade?desktop=${desktop}&componentType=${componentType}`
  );
