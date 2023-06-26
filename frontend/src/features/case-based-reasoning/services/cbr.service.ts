import api from "@/common/utils/axiosInstance";
import { ComputerDescription } from "@/features/types/ComputerDescription";

const prefix = "/cbr";

export const getAllDescriptions = async () => await api.get(prefix);

export const getSimilar = async (name: string) =>
  await api.get(`${prefix}/similar?name=${name}`);

export const getSimilarV2 = async (description: ComputerDescription) => {
  const path = `${prefix}/similar/v2?cpuCores=${description.cpuCores}&cpuSpeedGhz=${description.cpuSpeedGhz}&gpuSpeedMhz=${description.gpuSpeedMhz}&manufacturer=${description.manufacturer}&name=${description.name}&ramSizeGb=${description.ramSizeGb}&ramSpeedMhz=${description.ramSpeedMhz}&ramType=${description.ramType}&releaseYear=${description.releaseYear}&storageGb=${description.storageGb}`;
  return await api.get(path);
};
