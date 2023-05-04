package ftn.knowledge.engineering.ComputerRecommender.service;

import ftn.knowledge.engineering.ComputerRecommender.constants.FilePaths;
import ftn.knowledge.engineering.ComputerRecommender.constants.PropertyIris;
import ftn.knowledge.engineering.ComputerRecommender.dto.CpuDto;
import ftn.knowledge.engineering.ComputerRecommender.model.*;
import ftn.knowledge.engineering.ComputerRecommender.repository.OntologyRepository;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class OntologyServiceImpl implements OntologyService {
    private final OntologyRepository repository;

    @Autowired
    public OntologyServiceImpl(OntologyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<String> recommendCpus(Double clockSpeed, Integer coreCount, String manufacturer, Double minimumPrice, Double maximumPrice) {
        List<OWLNamedIndividual> cpuIndividuals = repository.getCpuIndividuals();
        List<String> recommendations = new ArrayList<>();
        for (OWLNamedIndividual individual : cpuIndividuals) {
            var cs = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.clockSpeedIri);
            double individualClockSpeed = cs.size() > 0 ? Double.parseDouble(cs.get(0).getLiteral()) : 0.0;

            var cc = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.coreCountIri);
            int individualCoreCount = cc.size() > 0 ? Integer.parseInt(cc.get(0).getLiteral()) : 0;

            var m = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.hardwareManufacturerIri);
            String individualManufacturer = m.size() > 0 ? m.get(0).getLiteral() : "";

            var p = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri);
            double individualPrice = p.size() > 0 ? Double.parseDouble(p.get(0).getLiteral()) : 0;

            if (individualCoreCount >= coreCount && individualClockSpeed >= clockSpeed && individualManufacturer.contains(manufacturer) && individualPrice >= minimumPrice && individualPrice <= maximumPrice) {
                recommendations.add(individual.getIRI().getShortForm());
            }

        }

        return recommendations;
    }

    @Override
    public List<String> recommendRams(String type, Integer size, Integer latency, Integer frequency, String manufacturer, Double minimumPrice, Double maximumPrice) {
        List<OWLNamedIndividual> ramIndividuals = repository.getRamIndividuals();
        List<String> recommendations = new ArrayList<>();
        for (OWLNamedIndividual individual : ramIndividuals) {
            var t = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.ramTypeIri);
            String individualRamType = t.size() > 0 ? t.get(0).getLiteral() : "";

            var s = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.ramSizeIri);
            double individualRamSize = s.size() > 0 ? Integer.parseInt(s.get(0).getLiteral()) : 0.0;

            var cl = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.casLatencyIri);
            int individualCasLatency = cl.size() > 0 ? Integer.parseInt(cl.get(0).getLiteral()) : 0;

            var f = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.frequencyIri);
            int individualFrequency = f.size() > 0 ? Integer.parseInt(f.get(0).getLiteral()) : 0;

            var m = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.hardwareManufacturerIri);
            String individualManufacturer = m.size() > 0 ? m.get(0).getLiteral() : "";

            var p = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri);
            double individualPrice = p.size() > 0 ? Double.parseDouble(p.get(0).getLiteral()) : 0;

            if (individualRamType.contains(type) && individualCasLatency <= latency && individualRamSize >= size && individualFrequency >= frequency && individualManufacturer.contains(manufacturer) && individualPrice >= minimumPrice && individualPrice <= maximumPrice) {
                recommendations.add(individual.getIRI().getShortForm());
            }

        }

        return recommendations;
    }

    @Override
    public List<OWLNamedIndividual> upgradeChipset(Chipset chipset) {
        List<OWLNamedIndividual> chipsets = repository.getChipsetIndividuals();
        List<OWLNamedIndividual> upgrades = new ArrayList<>();
        for (OWLNamedIndividual individual : chipsets) {
            var ct =  this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.chipsetTypeIri);
            String chipsetType = ct.get(0).getLiteral();
            if (chipset.getType().toString().equals(chipsetType)) {
                if (!chipset.getName().equals(individual.getIRI().getShortForm())) {
                    upgrades.add(individual);
                }
            }
        }
        return upgrades;
    }
    public List<OWLNamedIndividual> getMotherboardsByType(MotherboardType type){
        List<OWLNamedIndividual> motherboards = repository.getMotherboardIndividuals();
        List<OWLNamedIndividual> motherboardsByType = new ArrayList<>();
        for (OWLNamedIndividual individual : motherboards) {
            if (type.toString().equals(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.motherboardTypeIri).get(0).getLiteral())) {
                motherboardsByType.add(individual);
            }
        }
        return motherboardsByType;
    }

    @Override
    public List<OWLNamedIndividual> upgradeMotherboard(Motherboard motherboard) {
        List<OWLNamedIndividual> upgradeCandidates = getMotherboardsByType(motherboard.getType());
        List<OWLNamedIndividual> upgrades = new ArrayList<>();
        for(OWLNamedIndividual individual : upgradeCandidates){
            if(Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.numberOfRAMSlotsIri).get(0).getLiteral())> motherboard.getNumberOfRAMSlots()){
                upgrades.add(individual);
            }
        }
        return upgrades;
    }
    public List<OWLNamedIndividual> getGPUByManufacturer(String manufacturer){
        List<OWLNamedIndividual> GPUs = repository.getGPUIndividuals();
        List<OWLNamedIndividual> GPUsByManufacturer = new ArrayList<>();
        for(OWLNamedIndividual individual : GPUs){
            if(manufacturer.equals(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.hardwareManufacturerIri).get(0).getLiteral())){
                GPUsByManufacturer.add(individual);
            }
        }
        return GPUsByManufacturer;
        }
    @Override
    public List<OWLNamedIndividual> upgradeGPU(GPU gpu) {
        List<OWLNamedIndividual> upgradeCandidates = getGPUByManufacturer(gpu.getManufacturer());
        List<OWLNamedIndividual> upgrades = new ArrayList<>();
        for(OWLNamedIndividual individual : upgradeCandidates){
            if(Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.vRAMSizeIri).get(0).getLiteral())> gpu.getVRAMSize()){
                upgrades.add(individual);
            } else if (Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.boostClockSpeedIri).get(0).getLiteral())>gpu.getBoostClockSpeed()) {
                upgrades.add(individual);
            }
        }
        return upgrades;
    }

}
