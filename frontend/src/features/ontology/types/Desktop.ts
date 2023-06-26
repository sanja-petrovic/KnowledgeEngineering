interface Base {
  name: string;
  manufacturer: string;
  priceEur: string;
}

export interface Desktop extends Base {
  motherboard: Motherboard;
  gpu: Gpu;
  powerSupply: PowerSupply;
  storage: Storage;
}

export interface Motherboard extends Base {
  ram: string;
  numberOfRAMSlots: number;
  type: string;
  cpu: Cpu;
  chipset: Chipset;
}

export interface Gpu extends Base {
  GPUType: string;
  clockSpeed: number;
  boostClockSpeed: number;
  vRAMSize: number;
}

export interface Chipset extends Base {
  type: string;
}

export interface Cpu extends Base {
  generaton: string;
  clockSpeed: number;
  coreCount: number;
  threadCount: number;
  tdp: number;
  cacheSizes: number[];
  supportsMultithreading: boolean;
  supportsOverclocking: boolean;
  hasIntegratedGraphics: boolean;
}

export interface PowerSupply extends Base {
  wattage: number;
}

export interface Storage extends Base {
  type: string;
  memoryCapacity: number;
  writeSpeed: number;
}
