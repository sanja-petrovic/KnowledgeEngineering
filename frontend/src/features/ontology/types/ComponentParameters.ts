export interface ComponentParameters {
  manufacturer: string;
  minimumPrice: number;
  maximumPrice: number;
}

export interface CpuParameters extends ComponentParameters {
  clockSpeed: number;
  coreCount: number;
}

export interface GpuParameters extends ComponentParameters {
  clockSpeedMin: number;
  clockSpeedMax: number;
  maxVRAM: number;
  minVRAM: number;
}

export interface RamParameters extends ComponentParameters {
  frequency: number;
  latency: number;
  size: number;
  type: "ddr" | "ddr2" | "ddr3" | "ddr4" | "ddr5";
}

export interface PowerSupplyParameters extends ComponentParameters {
  wattage: number;
}

export interface MotherboardParameters extends ComponentParameters {
  type: string;
  minRAM: number;
  maxRAM: number;
}

export interface StorageParameters extends ComponentParameters {
  memoryMax: number;
  memoryMin: number;
  writeSpeedMin: number;
  type: string;
}

export interface Parameters {
  memoryMax: number;
  memoryMin: number;
  writeSpeedMin: number;
  type: string;
  minRAM: number;
  maxRAM: number;
  wattage: number;
  frequency: number;
  latency: number;
  size: number;
  clockSpeedMin: number;
  clockSpeedMax: number;
  maxVRAM: number;
  minVRAM: number;
  clockSpeed: number;
  coreCount: number;
  manufacturer: string;
  minimumPrice: number;
  maximumPrice: number;
}
