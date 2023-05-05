package ftn.knowledge.engineering.ComputerRecommender.service;

import ftn.knowledge.engineering.ComputerRecommender.model.Chipset;
import ftn.knowledge.engineering.ComputerRecommender.model.GPU;
import ftn.knowledge.engineering.ComputerRecommender.model.Motherboard;
import ftn.knowledge.engineering.ComputerRecommender.model.MotherboardType;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface OntologyService {
    List<String> recommendCpus(Double clockSpeed, Integer coreCount, String manufacturer, Double minimumPrice, Double maximumPrice);
    List<String> recommendRams(String type, Integer size, Integer latency, Integer frequency, String manufacturer, Double minimumPrice, Double maximumPrice);
    List<String> recommendCpuUpgrades(String cpuModel);
    List<String> recommendRamUpgrades(String ramModel);
    List<String> recommendChipsets(String chipsetType);
    List<String> recommendMotherboards(String type, double maxPrice, double minPrice, String manufacturer, int minRAMSlots, int maxRAMSlots);
    List<String> recommendGPU(double maxPrice, double minPrice, String manufacturer, int minVRAM, int maxVRAM, double minClockSpeed, double maxClockSpeed);
    List<String> recommendStorage(String manufacturer, String type, Integer minMemoryCapacity, Integer maxMemoryCapacity, Double minPrice, Double maxPrice, Double WriteSpeedMin);
    List<String> recommendSPowerSupply(String manufacturer, String type, Integer wattage, Integer inputVoltageMin, Integer inputVoltageMax, Integer outputVoltage, Double inputAmperage, Double outputAmperage, Double minPrice, Double maxPrice);
    List<OWLNamedIndividual> upgradeChipset(Chipset chipset);

    List<OWLNamedIndividual> upgradeMotherboard(Motherboard motherboard);

    List<OWLNamedIndividual> upgradeGPU(GPU gpu);

    List<OWLNamedIndividual> getMotherboardsByType(MotherboardType type);
}
