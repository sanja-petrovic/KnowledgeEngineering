import api from "@/common/utils/axiosInstance";
import { FuzzyInput } from "../types/FuzzyInput";

const prefix = "/fuzzy";

export const evaluateSuitability = async (input: FuzzyInput) =>
  await api.post(prefix, input);
