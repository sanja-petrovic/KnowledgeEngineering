package ftn.knowledge.engineering.ComputerRecommender.service.ontology;

import ftn.knowledge.engineering.ComputerRecommender.model.*;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface OntologyService {
    List<String> recommendCpus(Double clockSpeed, Integer coreCount, String manufacturer, Double minimumPrice, Double maximumPrice);
    List<String> recommendRams(String type, Integer size, Integer latency, Integer frequency, String manufacturer, Double minimumPrice, Double maximumPrice);
    List<String> recommendCpuUpgradesForDesktop(Desktop desktop);
    List<String> recommendRamUpgradesForDesktop(Desktop desktop);
    List<String> recommendUpgradesDesktop(Desktop desktop, String componentType);
    List<String> recommendUpgradesComponents(List<String> components, String componentType);
    List<String> recommendChipsets(String chipsetType);
    List<String> recommendMotherboards(String type, double maxPrice, double minPrice, String manufacturer, int minRAMSlots, int maxRAMSlots);
    List<String> recommendGPU(double maxPrice, double minPrice, String manufacturer, int minVRAM, int maxVRAM, double minClockSpeed, double maxClockSpeed);
    List<String> recommendStorage(String manufacturer, String type, Integer minMemoryCapacity, Integer maxMemoryCapacity, Double minPrice, Double maxPrice, Double WriteSpeedMin);
    List<String> recommendPowerSupply(String manufacturer, String type, Integer wattage, Integer inputVoltageMin, Integer inputVoltageMax, Integer outputVoltage, Double inputAmperage, Double outputAmperage, Double minPrice, Double maxPrice);
    List<String> upgradeChipset(Chipset chipset);

    List<String> upgradeMotherboard(Motherboard motherboard);

    List<String> upgradeGPU(GPU gpu);
    List<OWLNamedIndividual> getDesktops();
    List<OWLNamedIndividual> getMotherboardsByType(MotherboardType type);
    OWLNamedIndividual getCPUByName(String name);
    OWLNamedIndividual getMotherboardByName(String name);
    OWLNamedIndividual getGPUByName(String name);
    OWLNamedIndividual getDesktopByName(String name);

}
