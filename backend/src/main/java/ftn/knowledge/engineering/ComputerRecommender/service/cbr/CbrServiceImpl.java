package ftn.knowledge.engineering.ComputerRecommender.service.cbr;

import es.ucm.fdi.gaia.jcolibri.casebase.LinealCaseBase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCaseBase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRQuery;
import es.ucm.fdi.gaia.jcolibri.exception.ExecutionException;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.EqualsStringIgnoreCase;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Threshold;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.RetrievalResult;
import es.ucm.fdi.gaia.jcolibri.method.retrieve.selection.SelectCases;
import ftn.knowledge.engineering.ComputerRecommender.dto.CbrOutput;
import ftn.knowledge.engineering.ComputerRecommender.dto.CbrResult;
import ftn.knowledge.engineering.ComputerRecommender.model.ComputerDescription;
import ftn.knowledge.engineering.ComputerRecommender.repository.cbr.CbrRepository;
import ftn.knowledge.engineering.ComputerRecommender.similarity.Similarity;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CbrServiceImpl implements CbrService {
    private final CbrRepository repository;
    private final CBRCaseBase cbrCaseBase;
    private final NNConfig simConfig;

    public CbrServiceImpl(CbrRepository repository) {
        this.repository = repository;
        this.cbrCaseBase = new LinealCaseBase();
        this.simConfig = new NNConfig();
    }

    public List<ComputerDescription> getDescriptions() {
        return this.repository.getDescriptions();
    }
    public ComputerDescription getDescription(String name) {
        return this.repository.getDescription(name);
    }

    public List<CbrResult> getSimilar(String name) {
        ComputerDescription input = this.getDescription(name);
        List<CbrResult> similar = new ArrayList<>();
        CBRQuery query = new CBRQuery();
        query.setDescription(input);
        try {
            this.configure();
            this.preCycle();
            Collection<RetrievalResult> evaluated = NNScoringMethod.evaluateSimilarity(cbrCaseBase.getCases(), query, simConfig);
            Collection<RetrievalResult> selected = SelectCases.selectTopKRR(evaluated, 6);
            for (RetrievalResult selection : selected) {
                ComputerDescription description = (ComputerDescription) selection.get_case().getDescription();
                if (!description.getName().equalsIgnoreCase(input.getName())) {
                    similar.add(new CbrResult(description, selection.getEval()));
                }
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return similar;
    }

    @Override
    public void configure() {
        this.simConfig.setDescriptionSimFunction(new Average());
        this.addMappingToConfig("releaseYear", new Threshold(4));
        this.addMappingToConfig("cpuCores", new Equal());
        this.addMappingToConfig("cpuSpeedGhz", new Interval(0.3));
        this.addMappingToConfig("manufacturer", new EqualsStringIgnoreCase());
        this.addMappingToConfig("ramSizeGb", new Equal());
        this.addMappingToConfig("ramSpeedMhz", new Interval(400));
        this.addMappingToConfig("gpuSpeedMhz", new Interval(250));
        this.addMappingToConfig("storageGb", new Equal());
        Similarity similarity = new Similarity(new String[] {"ddr, ddr2, ddr3, ddr4, ddr5"});
        similarity.setSimilarity("ddr4", "ddr5", 0.8);
        similarity.setSimilarity("ddr4", "ddr3", 0.2);
        this.addMappingToConfig("ramType", similarity);
    }

    private void addMappingToConfig(String attributeName, LocalSimilarityFunction similarityFunction) {
        this.simConfig.addMapping(new Attribute(attributeName, ComputerDescription.class), similarityFunction);
    }

    @Override
    public CBRCaseBase preCycle() throws ExecutionException {
        cbrCaseBase.init(repository);
        return cbrCaseBase;
    }

    @Override
    public void cycle(CBRQuery cbrQuery) {
    }

    @Override
    public void postCycle() {
        throw new NotImplementedException();
    }
}
