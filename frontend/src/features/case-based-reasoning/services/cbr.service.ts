import api from "@/common/utils/axiosInstance";

const prefix = "/cbr";

export const getAllDescriptions = async () => await api.get(prefix);

export const getSimilar = async (name: string) =>
  await api.get(`${prefix}/similar?name=${name}`);
