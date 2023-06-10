package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.constants.PropertyIris;
import ftn.knowledge.engineering.ComputerRecommender.model.*;
import ftn.knowledge.engineering.ComputerRecommender.repository.OntologyRepository;
import ftn.knowledge.engineering.ComputerRecommender.service.ontology.OntologyService;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MotherboardConverterImpl implements MotherboardConverter{
    private final OntologyRepository repository;
    private final OntologyService service;
    private final CpuConverter cpuConverter;
    private final ChipsetConverter chipsetConverter;
    @Autowired
    public MotherboardConverterImpl(OntologyRepository repository, CpuConverter cpuConverter, OntologyService service, ChipsetConverter chipsetConverter) {
        this.repository = repository;
        this.cpuConverter = cpuConverter;
        this.service = service;
        this.chipsetConverter = chipsetConverter;
    }

    @Override
    public List<Motherboard> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals) {
        List<Motherboard> converted = new ArrayList<>();
        for(OWLNamedIndividual individual : individuals){
           List<OWLNamedIndividual> chipsets = new ArrayList<>();
            chipsets.add(this.repository.getObjectPropertyValueOfChipsetFromSuperclass(individual).get(0));
            Chipset chipset = chipsetConverter.convertFromOwlIndividuals(chipsets).get(0);
            List<OWLNamedIndividual> cpus = new ArrayList<>();
            cpus.add(this.service.getCPUByName(this.repository.getObjectPropertyValueOfIndividual(individual, PropertyIris.motherboardCpuIri).get(0).getIRI().getShortForm()));
            CPU cpu = new CPU();
            if(!cpus.isEmpty()) {
                cpu = cpuConverter.convertFromOwlIndividuals(cpus).get(0);
            }
            String ram = this.repository.getObjectPropertyValueOfRAMFromSuperclass(individual).get(0).getIRI().getShortForm();
            if(ram ==null ){
                ram = "";
            }

            Motherboard motherboard = new Motherboard(individual.getIRI().getShortForm(),
                    this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.hardwareManufacturerIri).get(0).getLiteral(),
                    Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri).get(0).getLiteral()),
                    PlatformCompatibility.DESKTOP,
                    Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.numberOfRAMSlotsIri).get(0).getLiteral()),
                    MotherboardType.valueOf(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.motherboardTypeIri).get(0).getLiteral()),
                    cpu,
                    chipset,
                    ram
                    );
            converted.add(motherboard);
        }
        return converted;
    }
}
