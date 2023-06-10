package ftn.knowledge.engineering.ComputerRecommender.service.bayes;

import ftn.knowledge.engineering.ComputerRecommender.dto.BayesInput;
import ftn.knowledge.engineering.ComputerRecommender.dto.BayesOutput;

import java.util.List;

public interface BayesService {
    List<BayesOutput> evaluateCause(BayesInput input);
}
