package ftn.knowledge.engineering.ComputerRecommender.service.bayes;

import ftn.knowledge.engineering.ComputerRecommender.dto.BayesInput;
import ftn.knowledge.engineering.ComputerRecommender.dto.BayesOutput;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import unbbayes.io.NetIO;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

import javax.annotation.PostConstruct;

import static ftn.knowledge.engineering.ComputerRecommender.constants.FilePaths.bayesNetwork;

@Service
public class BayesServiceImpl implements BayesService {
    private final ProbabilisticNetwork network;
    private final IInferenceAlgorithm algorithm;

    public BayesServiceImpl() {
        try {
            this.network = (ProbabilisticNetwork) new NetIO().load(new File(bayesNetwork));
            this.algorithm = new JunctionTreeAlgorithm(this.network);
        } catch (IOException e) {
            throw new RuntimeException("Error loading network.");
        }
    }

    @Override
    public List<BayesOutput> evaluateCause(BayesInput bayesInput) {
        this.algorithm.run();
        for (String symptom : bayesInput.symptoms()) {
            ProbabilisticNode node = (ProbabilisticNode) network.getNode(symptom);
            node.addFinding(0);
        }
        try {
            network.updateEvidences();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

        List<BayesOutput> output = new ArrayList<>();
        for (String symptom : bayesInput.symptoms()) {
            for (Node parent : this.network.getNode(symptom).getParents()) {
                BayesOutput parentOutput = new BayesOutput(parent.getName(), ((ProbabilisticNode) parent).getMarginalAt(0) * 100);
                if (!output.contains(parentOutput) && !parentOutput.cause().equalsIgnoreCase(symptom)) {
                    output.add(parentOutput);
                }
                for (Node grandparent : this.network.getNode(parent.getName()).getParents()) {
                    BayesOutput grandparentOutput = new BayesOutput(grandparent.getName(), ((ProbabilisticNode) grandparent).getMarginalAt(0) * 100);
                    if (!output.contains(grandparentOutput) && !grandparentOutput.cause().equalsIgnoreCase(symptom)) {
                        output.add(grandparentOutput);
                    }
                }
            }
        }

        return output;
    }

    @Override
    public List<String> getSymptoms() {
        List<String> symptoms = new ArrayList<>();
        for (Node node : this.network.getNodes()) {
            if (node.getChildren().size() == 0) {
                symptoms.add(node.getName());
            }
        }
        return symptoms;
    }
}
