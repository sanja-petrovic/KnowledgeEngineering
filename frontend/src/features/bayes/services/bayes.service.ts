import api from "@/common/utils/axiosInstance";

const prefix = "/bayes";

export const getSymptoms = async () => await api.get(`${prefix}/symptoms`);
