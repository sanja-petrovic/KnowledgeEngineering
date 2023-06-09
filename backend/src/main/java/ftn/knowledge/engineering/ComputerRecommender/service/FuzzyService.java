package ftn.knowledge.engineering.ComputerRecommender.service;

import ftn.knowledge.engineering.ComputerRecommender.dto.FuzzyInput;
import ftn.knowledge.engineering.ComputerRecommender.dto.FuzzyOutput;

public interface FuzzyService {

    FuzzyOutput evaluate(FuzzyInput input);
}
