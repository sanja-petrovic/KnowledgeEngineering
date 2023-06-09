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
    public List<String> recommendCpuUpgrades(Desktop desktop) {
        List<OWLNamedIndividual> cpuIndividuals = this.repository.getCpuIndividuals();
        List<String> possibleUpgrades = new ArrayList<>();

        OWLNamedIndividual enteredCpu = this.getIndividual(cpuIndividuals, desktop.getMotherboard().getCpu().getName());
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
            boolean isCompatibleWithChipset = false;
            for(OWLNamedIndividual chipset : this.repository.getObjectPropertyValueOfChipsetsForCpusFromSuperclass(cpu))
            {
                if(chipset.getIRI().getShortForm().equals(desktop.getMotherboard().getChipset().getName()))
                {
                    isCompatibleWithChipset = true;
                }
            }
            if (isSameManufacturer && (isBetterClockSpeed || isBetterCoreCount)&& isCompatibleWithChipset) {
                possibleUpgrades.add(cpu.getIRI().getShortForm());
            }
        }

        return possibleUpgrades;
    }

    @Override
    public List<String> recommendRamUpgrades(Desktop desktop) {
        List<OWLNamedIndividual> ramIndividuals = this.repository.getRamIndividuals();
        List<String> possibleUpgrades = new ArrayList<>();

        OWLNamedIndividual enteredRam = this.getIndividual(ramIndividuals, desktop.getMotherboard().getRam());
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

            boolean isSameType = RAMType.valueOf(individualRamType).ordinal() == RAMType.valueOf(enteredRamType).ordinal();
            boolean isBiggerSize = individualRamSize > enteredRamSize;
            boolean isBetterFrequency = individualFrequency > enteredFrequency;

            if (isSameType && (isBiggerSize || isBetterFrequency)) {
                possibleUpgrades.add(ram.getIRI().getShortForm());
            }
        }

        return possibleUpgrades;
    }

    @Override
    public List<String> recommendChipsets(String chipsetType) {
        List<OWLNamedIndividual> chipsets = repository.getChipsetIndividuals();
        List<String> recommendations = new ArrayList<>();
        for (OWLNamedIndividual individual : chipsets) {
            String type = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.chipsetTypeIri).get(0).getLiteral();
            if (chipsetType.equals(type)) {
                    recommendations.add(individual.getIRI().getShortForm());
            }
        }
        return recommendations;
    }


    public List<OWLNamedIndividual> FilterByManufacturer(List<OWLNamedIndividual>individuals,String manufacturer){
        List<OWLNamedIndividual> filtered = new ArrayList<>();
        for(OWLNamedIndividual individual : individuals){
            String individualManufacturer = this.repository.getDataPropertyValueOfIndividual(individual,PropertyIris.hardwareManufacturerIri).get(0).getLiteral();
            if(individualManufacturer.equals(manufacturer))
                filtered.add(individual);
        }
        return filtered;
    }


    @Override
    public List<String> recommendMotherboards(String type, double maxPrice, double minPrice, String manufacturer, int minRAMSlots, int maxRAMSlots) {
        List<OWLNamedIndividual> motherboards = new ArrayList<>();
        if(!type.equals("any")){
            motherboards = getMotherboardsByType(MotherboardType.valueOf(type));
        } else motherboards = this.repository.getMotherboardIndividuals();
        if(!manufacturer.equals("any"))
            motherboards = FilterByManufacturer(motherboards,manufacturer);
        List<String> recommendations = new ArrayList<>();
        for(OWLNamedIndividual individual : motherboards){
            Integer individualRAM = Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual,PropertyIris.numberOfRAMSlotsIri).get(0).getLiteral());
            Double individualPrice = Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual,PropertyIris.priceIri).get(0).getLiteral());
            if(individualPrice<=maxPrice && individualPrice >= minPrice && individualRAM<= maxRAMSlots && individualRAM>=minRAMSlots){
                recommendations.add(individual.getIRI().getShortForm());
            }
        }
        return recommendations;
    }

    @Override
    public List<String> recommendGPU(double maxPrice, double minPrice, String manufacturer, int minVRAM, int maxVRAM, double minClockSpeed, double maxClockSpeed) {
        List<OWLNamedIndividual> gpus = new ArrayList<>();
        if(!manufacturer.equals("any")) {
            gpus = getGPUByManufacturer(manufacturer);
        }
        else gpus = this.repository.getGPUIndividuals();
        List<String> recommendations = new ArrayList<>();
        for(OWLNamedIndividual individual : gpus){
            int individualVRAM = Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual,PropertyIris.vRAMSizeIri).get(0).getLiteral());
            double individualPrice = Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual,PropertyIris.priceIri).get(0).getLiteral());
            double individualClockSpeed = Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual,PropertyIris.clockSpeedIri).get(0).getLiteral());
            double individualBoostClockSpeed = Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual,PropertyIris.boostClockSpeedIri).get(0).getLiteral());
            if(individualPrice<=maxPrice && individualPrice >= minPrice && individualVRAM<= maxVRAM && individualVRAM>=minVRAM && individualClockSpeed>=minClockSpeed && individualBoostClockSpeed<=maxClockSpeed){
                recommendations.add(individual.getIRI().getShortForm());
            }
        }
        return recommendations;
    }

    @Override
    public List<String> recommendStorage(String manufacturer, String type, Integer minMemoryCapacity, Integer maxMemoryCapacity, Double minPrice, Double maxPrice, Double writeSpeedMin) {
        List<OWLNamedIndividual> storageIndividuals = repository.getStorageIndividuals();
        List<String> recommendations = new ArrayList<>();
        for (OWLNamedIndividual individual : storageIndividuals) {
            var m = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.hardwareManufacturerIri);
            String individualManufacturer = m.size() > 0 ? m.get(0).getLiteral() : "";

            var t = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.storageTypeIri);
            String individualStorageType = t.size() > 0 ? t.get(0).getLiteral() : "";

            var mc = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.memoryCapacityIri);
            int individualMemoryCapacity = mc.size() > 0 ? Integer.parseInt(mc.get(0).getLiteral()) : 0;

            var p = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri);
            double individualPrice = p.size() > 0 ? Double.parseDouble(p.get(0).getLiteral()) : 0;

            var ws = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.writeSpeedIri);
            double individualWriteSpeed = ws.size() > 0 ? Double.parseDouble(ws.get(0).getLiteral()) : 0;

            if(individualStorageType.contains(type) && individualManufacturer.contains(manufacturer) && individualPrice >= minPrice && individualPrice <= maxPrice && individualMemoryCapacity >= minMemoryCapacity && individualMemoryCapacity <= maxMemoryCapacity && individualWriteSpeed >= writeSpeedMin){
                recommendations.add(individual.getIRI().getShortForm());
            }
        }
        return recommendations;
    }

    @Override
    public List<String> recommendSPowerSupply(String manufacturer, String type, Integer wattage, Integer inputVoltageMin, Integer inputVoltageMax, Integer outputVoltage, Double inputAmperage, Double outputAmperage, Double minPrice, Double maxPrice) {
        List<OWLNamedIndividual> powerSupplyIndividuals = repository.getPowerSupplyIndividuals();
        List<String> recommendations = new ArrayList<>();
        for (OWLNamedIndividual individual : powerSupplyIndividuals) {
            var m = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.hardwareManufacturerIri);
            String individualManufacturer = m.size() > 0 ? m.get(0).getLiteral() : "";

            var t = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.powerSupplyTypeIri);
            String individualPowerSupplyType = t.size() > 0 ? t.get(0).getLiteral() : "";

            var p = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri);
            double individualPrice = p.size() > 0 ? Double.parseDouble(p.get(0).getLiteral()) : 0;

            var w = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.wattageIri);
            int individualWattage = w.size() > 0 ? Integer.parseInt(w.get(0).getLiteral()) : 0;

            var ivmin = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.inputVoltageMinIri);
            int individualInputVoltageMin = ivmin.size() > 0 ? Integer.parseInt(ivmin.get(0).getLiteral()) : 0;

            var ivmax = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.inputVoltageMaxIri);
            int individualInputVoltageMax = ivmax.size() > 0 ? Integer.parseInt(ivmax.get(0).getLiteral()) : 0;

            var ov = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.outputVoltageIri);
            int individualOutputVoltage = ov.size() > 0 ? Integer.parseInt(ov.get(0).getLiteral()) : 0;

            var ia = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.inputAmperageIri);
            double individualInputAmperage = ia.size() > 0 ? Double.parseDouble(ia.get(0).getLiteral()) : 0;

            var oa = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.outputAmperageIri);
            double individualOutputAmperage = oa.size() > 0 ? Double.parseDouble(oa.get(0).getLiteral()) : 0;

            if(individualPowerSupplyType.contains(type) && individualManufacturer.contains(manufacturer) && individualPrice >= minPrice && individualPrice <= maxPrice && individualWattage >= wattage && individualInputVoltageMin <= inputVoltageMin && individualInputVoltageMax <= inputVoltageMax && individualOutputVoltage <= outputVoltage && individualOutputAmperage <= outputAmperage && individualInputAmperage <= inputAmperage){
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
            var ct = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.chipsetTypeIri);
            String chipsetType = ct.get(0).getLiteral();
            if (chipset.getType().toString().equals(chipsetType)) {
                if (!chipset.getName().equals(individual.getIRI().getShortForm())) {
                    upgrades.add(individual);
                }
            }
        }
        return upgrades;
    }

    public List<OWLNamedIndividual> getMotherboardsByType(MotherboardType type) {
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
    public OWLNamedIndividual getCPUByName(String name) {
        return this.getByName(name, repository.getCpuIndividuals());

    }

    @Override
    public OWLNamedIndividual getMotherboardByName(String name) {
        return this.getByName(name, repository.getMotherboardIndividuals());
    }

    @Override
    public OWLNamedIndividual getGPUByName(String name)
    {
        return this.getByName(name, repository.getGPUIndividuals());
    }

    @Override
    public OWLNamedIndividual getDesktopByName(String name) {
        return this.getByName(name, this.getDesktops());
    }

    public OWLNamedIndividual getByName(String name, List<OWLNamedIndividual> individuals){
        for(OWLNamedIndividual individual : individuals){
            if(individual.getIRI().getShortForm().equals(name)){
                return individual;
            }
        }
        return null;
    }

    @Override
    public List<OWLNamedIndividual> upgradeMotherboard(Motherboard motherboard) {
        List<OWLNamedIndividual> upgradeCandidates = getMotherboardsByType(motherboard.getType());
        List<OWLNamedIndividual> upgrades = new ArrayList<>();
        for (OWLNamedIndividual individual : upgradeCandidates) {
            if (Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.numberOfRAMSlotsIri).get(0).getLiteral()) > motherboard.getNumberOfRAMSlots()) {
                upgrades.add(individual);
            }
        }
        return upgrades;
    }

    public List<OWLNamedIndividual> getGPUByManufacturer(String manufacturer) {
        List<OWLNamedIndividual> GPUs = repository.getGPUIndividuals();
        List<OWLNamedIndividual> GPUsByManufacturer = new ArrayList<>();
        for (OWLNamedIndividual individual : GPUs) {
            if (manufacturer.equals(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.hardwareManufacturerIri).get(0).getLiteral())) {
                GPUsByManufacturer.add(individual);
            }
        }
        return GPUsByManufacturer;
    }

    @Override
    public List<OWLNamedIndividual> upgradeGPU(GPU gpu) {
        List<OWLNamedIndividual> upgradeCandidates = getGPUByManufacturer(gpu.getManufacturer());
        List<OWLNamedIndividual> upgrades = new ArrayList<>();
        for (OWLNamedIndividual individual : upgradeCandidates) {
            if ((Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.vRAMSizeIri).get(0).getLiteral()) > gpu.getVRAMSize())&&
            (Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.boostClockSpeedIri).get(0).getLiteral()) > gpu.getBoostClockSpeed())) {
                upgrades.add(individual);
            }
        }
        return upgrades;
    }

    @Override
    public List<OWLNamedIndividual> getDesktops() {
        return this.repository.getDesktopIndividuals();
    }

}
