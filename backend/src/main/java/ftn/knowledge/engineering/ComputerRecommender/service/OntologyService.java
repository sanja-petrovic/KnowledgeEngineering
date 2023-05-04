package ftn.knowledge.engineering.ComputerRecommender.service;

import ftn.knowledge.engineering.ComputerRecommender.dto.CpuDto;
import ftn.knowledge.engineering.ComputerRecommender.model.CPU;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface OntologyService {
    void test();
    void recommendCpus(CpuDto dto);
    List<String> recommendCpus(Double clockSpeed, Integer coreCount, String manufacturer, Double minimumPrice, Double maximumPrice);
}
