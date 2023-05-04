package ftn.knowledge.engineering.ComputerRecommender.dto;

public record CpuDto(String manufacturer, double priceMin, double priceMax, String generation, double clockSpeed, int coreCount) {
}
