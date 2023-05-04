package ftn.knowledge.engineering.ComputerRecommender.service;

import ftn.knowledge.engineering.ComputerRecommender.dto.CpuDto;
import ftn.knowledge.engineering.ComputerRecommender.model.CPU;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface OntologyService {
    List<String> recommendCpus(Double clockSpeed, Integer coreCount, String manufacturer, Double minimumPrice, Double maximumPrice);
    List<String> recommendRams(String type, Integer size, Integer latency, Integer frequency, String manufacturer, Double minimumPrice, Double maximumPrice);
    List<String> recommendCpuUpgrades(String cpuModel);
}
