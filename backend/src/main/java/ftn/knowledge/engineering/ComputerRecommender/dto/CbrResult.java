package ftn.knowledge.engineering.ComputerRecommender.dto;

import ftn.knowledge.engineering.ComputerRecommender.model.ComputerDescription;

public record CbrResult(ComputerDescription description, double evaluation) {
}
