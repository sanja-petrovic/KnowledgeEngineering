package ftn.knowledge.engineering.ComputerRecommender.service;

import ftn.knowledge.engineering.ComputerRecommender.constants.PropertyIris;
import ftn.knowledge.engineering.ComputerRecommender.model.*;
import ftn.knowledge.engineering.ComputerRecommender.repository.OntologyRepository;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public OWLNamedIndividual getIndividual(List<OWLNamedIndividual> individuals, String name) {
        for (OWLNamedIndividual individual : individuals) {
            if (individual.getIRI().getShortForm().equals(name)) {
                return individual;
            }
        }

        return null;
    }

    @Override
    public List<String> recommendCpuUpgrades(String cpuModel) {
        List<OWLNamedIndividual> cpuIndividuals = this.repository.getCpuIndividuals();
        List<String> possibleUpgrades = new ArrayList<>();

        OWLNamedIndividual enteredCpu = this.getIndividual(cpuIndividuals, cpuModel);
        String enteredCpuManufacturer = this.repository.getDataPropertyValueOfIndividual(enteredCpu, PropertyIris.hardwareManufacturerIri).get(0).getLiteral();
        double enteredCpuClockSpeed = Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(enteredCpu, PropertyIris.clockSpeedIri).get(0).getLiteral());
        int enteredCoreCount = Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(enteredCpu, PropertyIris.coreCountIri).get(0).getLiteral());

        for (OWLNamedIndividual cpu : cpuIndividuals) {
            if (cpu.getIRI().equals(enteredCpu.getIRI())) continue;
            var cs = this.repository.getDataPropertyValueOfIndividual(cpu, PropertyIris.clockSpeedIri);
            double individualClockSpeed = cs.size() > 0 ? Double.parseDouble(cs.get(0).getLiteral()) : 0.0;

            var cc = this.repository.getDataPropertyValueOfIndividual(cpu, PropertyIris.coreCountIri);
            int individualCoreCount = cc.size() > 0 ? Integer.parseInt(cc.get(0).getLiteral()) : 0;

            var m = this.repository.getDataPropertyValueOfIndividual(cpu, PropertyIris.hardwareManufacturerIri);
            String individualManufacturer = m.size() > 0 ? m.get(0).getLiteral() : "";

            boolean isSameManufacturer = enteredCpuManufacturer.equals(individualManufacturer);
            boolean isBetterClockSpeed = individualClockSpeed > enteredCpuClockSpeed;
            boolean isBetterCoreCount = individualCoreCount > enteredCoreCount;

            if (isSameManufacturer && (isBetterClockSpeed || isBetterCoreCount)) {
                possibleUpgrades.add(cpu.getIRI().getShortForm());
            }
        }

        return possibleUpgrades;
    }

    @Override
    public List<String> recommendRamUpgrades(String ramModel) {
        List<OWLNamedIndividual> ramIndividuals = this.repository.getRamIndividuals();
        List<String> possibleUpgrades = new ArrayList<>();

        OWLNamedIndividual enteredRam = this.getIndividual(ramIndividuals, ramModel);
        int enteredRamSize = Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(enteredRam, PropertyIris.ramSizeIri).get(0).getLiteral());
        double enteredFrequency = Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(enteredRam, PropertyIris.frequencyIri).get(0).getLiteral());
        String enteredRamType = this.repository.getDataPropertyValueOfIndividual(enteredRam, PropertyIris.ramTypeIri).get(0).getLiteral();

        for (OWLNamedIndividual ram : ramIndividuals) {
            if (ram.getIRI().equals(enteredRam.getIRI())) continue;
            var f = this.repository.getDataPropertyValueOfIndividual(ram, PropertyIris.frequencyIri);
            double individualFrequency = f.size() > 0 ? Double.parseDouble(f.get(0).getLiteral()) : 0.0;

            var rs = this.repository.getDataPropertyValueOfIndividual(ram, PropertyIris.ramSizeIri);
            int individualRamSize = rs.size() > 0 ? Integer.parseInt(rs.get(0).getLiteral()) : 0;

            var rt = this.repository.getDataPropertyValueOfIndividual(ram, PropertyIris.ramTypeIri);
            String individualRamType = rt.size() > 0 ? rt.get(0).getLiteral() : "";

            boolean isSameOrBetterType = RAMType.valueOf(individualRamType).ordinal() >= RAMType.valueOf(enteredRamType).ordinal();
            boolean isBiggerSize = individualRamSize > enteredRamSize;
            boolean isBetterFrequency = individualFrequency > enteredFrequency;

            if (isSameOrBetterType && (isBiggerSize || isBetterFrequency)) {
                possibleUpgrades.add(ram.getIRI().getShortForm());
            }
        }

        return possibleUpgrades;
    }

    @Override
    public List<OWLNamedIndividual> upgradeChipset(Chipset chipset) {
        List<OWLNamedIndividual> chipsets = repository.getChipsetIndividuals();
        List<OWLNamedIndividual> upgrades = new ArrayList<>();
        for (OWLNamedIndividual individual : chipsets) {
            var ct =  this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.chipsetTypeIri);
            String chipsetType = ct.get(0).getLiteral();
            if (chipset.getType().toString().equals(chipsetType)) {
                if (!chipset.getName().equals(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.nameIri).get(0).getLiteral())) {
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
        for(OWLNamedIndividual individual : upgradeCandidates){
            if(Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.vRAMSizeIri).get(0).getLiteral())> gpu.getVRAMSize()){
                upgradeCandidates.add(individual);
            } else if (Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.boostClockSpeedIri).get(0).getLiteral())>gpu.getBoostClockSpeed()) {
                upgradeCandidates.add(individual);
            }
        }
        return upgradeCandidates;
    }

}
