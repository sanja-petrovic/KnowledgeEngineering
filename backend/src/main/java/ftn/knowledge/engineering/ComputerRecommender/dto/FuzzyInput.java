package ftn.knowledge.engineering.ComputerRecommender.dto;

public record FuzzyInput(double cpuClockSpeed, int cpuCoreCount, int cpuThreadCount, int gpuVramSize, int gpuClockSpeed,
                         int powerSupply, int ramSize, int ramSpeed, int storageCapacity) {
}