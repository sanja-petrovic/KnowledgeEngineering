package ftn.knowledge.engineering.ComputerRecommender.service;

import ftn.knowledge.engineering.ComputerRecommender.dto.FuzzyInput;
import ftn.knowledge.engineering.ComputerRecommender.dto.FuzzyOutput;
import net.sourceforge.jFuzzyLogic.FIS;
import org.springframework.stereotype.Service;
import static ftn.knowledge.engineering.ComputerRecommender.constants.FilePaths.fuzzySystems;

@Service
public class FuzzyServiceImpl implements FuzzyService {

    private FIS fis;

    public FuzzyServiceImpl() {
        this.fis = FIS.load(fuzzySystems, true);
    }

    @Override
    public FuzzyOutput evaluate(FuzzyInput input) {
        return null;
    }
}
