package ftn.knowledge.engineering.ComputerRecommender.service;

import ftn.knowledge.engineering.ComputerRecommender.dto.FuzzyInput;
import ftn.knowledge.engineering.ComputerRecommender.dto.FuzzyOutput;
import net.sourceforge.jFuzzyLogic.FIS;

public interface FuzzyService {

    FuzzyOutput evaluate(FuzzyInput input);
    void setVariables(FuzzyInput input);
}
