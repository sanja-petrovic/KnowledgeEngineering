package ftn.knowledge.engineering.ComputerRecommender.dto;

import ftn.knowledge.engineering.ComputerRecommender.model.ComputerDescription;

import java.util.List;

public record CbrOutput(List<ComputerDescription> computerDescription) {
}
