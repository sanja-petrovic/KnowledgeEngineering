package ftn.knowledge.engineering.ComputerRecommender.service;

import ftn.knowledge.engineering.ComputerRecommender.dto.FuzzyInput;
import ftn.knowledge.engineering.ComputerRecommender.dto.FuzzyOutput;
import net.sourceforge.jFuzzyLogic.FIS;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.springframework.stereotype.Service;
import static ftn.knowledge.engineering.ComputerRecommender.constants.FilePaths.fuzzySystems;

@Service
public class FuzzyServiceImpl implements FuzzyService {

    private FIS fis;
    private final OntologyService ontologyService;

    public FuzzyServiceImpl(OntologyService ontologyService) {
        this.ontologyService = ontologyService;
        this.fis = FIS.load(fuzzySystems, true);
    }

    @Override
    public FuzzyOutput evaluate(FuzzyInput input) {
        setVariables(input);
        fis.evaluate();
        return new FuzzyOutput(
                fis.getVariable("development").getValue(),
                fis.getVariable("video_games").getValue(),
                fis.getVariable("crypto").getValue(),
                fis.getVariable("hosting").getValue(),
                fis.getVariable("home").getValue(),
                fis.getVariable("business").getValue()
        );
    }

    @Override
    public void setVariables(FuzzyInput input) {
        fis.setVariable("cpu_clock_speed_ghz", input.cpuClockSpeed());
        fis.setVariable("cpu_core_count", input.cpuCoreCount());
        fis.setVariable("cpu_thread_count", input.cpuThreadCount());
        fis.setVariable("gpu_vram_size_gb", input.gpuVramSize());
        fis.setVariable("gpu_clock_speed_mhz", input.gpuClockSpeed());
        fis.setVariable("power_supply_watts", input.powerSupply());
        fis.setVariable("ram_size_gb", input.ramSize());
        fis.setVariable("ram_speed_mhz", input.ramSpeed());
        fis.setVariable("storage_capacity_gb", input.storageCapacity());
    }
}

