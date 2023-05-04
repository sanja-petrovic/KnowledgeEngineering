package ftn.knowledge.engineering.ComputerRecommender.service;

import ftn.knowledge.engineering.ComputerRecommender.dto.CpuDto;
import ftn.knowledge.engineering.ComputerRecommender.model.*;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface OntologyService {
    List<String> recommendCpus(Double clockSpeed, Integer coreCount, String manufacturer, Double minimumPrice, Double maximumPrice);
    List<String> recommendRams(String type, Integer size, Integer latency, Integer frequency, String manufacturer, Double minimumPrice, Double maximumPrice);
    List<String> recommendCpuUpgrades(String cpuModel);
    List<String> recommendRamUpgrades(String ramModel);
    List<OWLNamedIndividual> upgradeChipset(Chipset chipset);
    List<OWLNamedIndividual> upgradeMotherboard(Motherboard motherboard);
    List<OWLNamedIndividual> upgradeGPU(GPU gpu);
    List<OWLNamedIndividual> getMotherboardsByType(MotherboardType type);
}
