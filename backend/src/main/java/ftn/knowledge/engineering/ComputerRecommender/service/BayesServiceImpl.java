package ftn.knowledge.engineering.ComputerRecommender.service;

import lombok.SneakyThrows;
import net.sourceforge.jFuzzyLogic.FIS;
import org.springframework.stereotype.Service;
import unbbayes.io.NetIO;
import unbbayes.prs.Edge;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

@Service
public class BayesServiceImpl implements BayesService {


}
