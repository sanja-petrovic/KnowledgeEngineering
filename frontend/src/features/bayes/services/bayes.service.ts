import api from "@/common/utils/axiosInstance";
import { BayesInput } from "../types/BayesInput";

const prefix = "/bayes";

export const getSymptoms = async () => await api.get(`${prefix}/symptoms`);

export const evaluateCause = async (input: BayesInput) =>
  await api.post(`${prefix}/`, input);
