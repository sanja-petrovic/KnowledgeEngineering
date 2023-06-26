package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.constants.PropertyIris;
import ftn.knowledge.engineering.ComputerRecommender.model.*;
import ftn.knowledge.engineering.ComputerRecommender.repository.ontology.OntologyRepository;
import ftn.knowledge.engineering.ComputerRecommender.service.ontology.OntologyService;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class DesktopConverterImpl implements  DesktopConverter{
    private final OntologyRepository repository;
    private final OntologyService service;
    private final GPUConverter gpuConverter;
    private final MotherboardConverter motherboardConverter;
    private final StorageConverter storageConverter;
    private final PowerSupplyConverter powerSupplyConverter;
    public DesktopConverterImpl(OntologyService service, GPUConverter gpuConverter, MotherboardConverter motherboardConverter, OntologyRepository repository, StorageConverter storageConverter, PowerSupplyConverter powerSupplyConverter){
        this.service = service;
        this.gpuConverter = gpuConverter;
        this.motherboardConverter = motherboardConverter;
        this.repository = repository;
        this.storageConverter = storageConverter;
        this.powerSupplyConverter = powerSupplyConverter;
    }

    @Override
    public List<Desktop> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals) {
        List<Desktop> converted = new ArrayList<>();
        for(OWLNamedIndividual individual : individuals){
            List<OWLNamedIndividual> motherboard = new ArrayList<>();
            motherboard.add(this.service.getMotherboardByName(this.repository.getObjectPropertyValueOfIndividual(individual, PropertyIris.computerMotherboardIri).get(0).getIRI().getShortForm()));
            List<OWLNamedIndividual> gpu = new ArrayList<>();
            gpu.add(this.service.getGPUByName(this.repository.getObjectPropertyValueOfIndividual(individual, PropertyIris.computerGpuIri).get(0).getIRI().getShortForm()));
            List<OWLNamedIndividual> powerSupply = new ArrayList<>();
            powerSupply.add(this.service.getPowerSupplyByName(this.repository.getObjectPropertyValueOfIndividual(individual, PropertyIris.computerPowerSupplyIri).get(0).getIRI().getShortForm()));
            List<OWLNamedIndividual> storage = new ArrayList<>();
            storage.add(this.service.getStorageByName(this.repository.getObjectPropertyValueOfIndividual(individual, PropertyIris.computerStorage).get(0).getIRI().getShortForm()));
            Desktop desktop = new Desktop(individual.getIRI().getShortForm(),
                    individual.getIRI().getShortForm(),
                    Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri).get(0).getLiteral()),
                    PlatformCompatibility.DESKTOP,
                    motherboardConverter.convertFromOwlIndividuals(motherboard).get(0),
                    gpuConverter.convertFromOwlIndividuals(gpu).get(0),
                    this.powerSupplyConverter.convertFromOwlIndividuals(powerSupply).get(0),
                    this.storageConverter.convertFromOwlIndividuals(storage).get(0)
            );
            converted.add(desktop);
        }
        return converted;
    }

    @Override
    public Desktop convertFromOwlIndividual(OWLNamedIndividual individual) {
        List<OWLNamedIndividual> motherboard = new ArrayList<>();
        motherboard.add(this.service.getMotherboardByName(this.repository.getObjectPropertyValueOfIndividual(individual, PropertyIris.computerMotherboardIri).get(0).getIRI().getShortForm()));
        List<OWLNamedIndividual> gpu = new ArrayList<>();
        gpu.add(this.service.getGPUByName(this.repository.getObjectPropertyValueOfIndividual(individual, PropertyIris.computerGpuIri).get(0).getIRI().getShortForm()));
        List<OWLNamedIndividual> powerSupply = new ArrayList<>();
        powerSupply.add(this.service.getPowerSupplyByName(this.repository.getObjectPropertyValueOfIndividual(individual, PropertyIris.computerPowerSupplyIri).get(0).getIRI().getShortForm()));
        List<OWLNamedIndividual> storage = new ArrayList<>();
        storage.add(this.service.getStorageByName(this.repository.getObjectPropertyValueOfIndividual(individual, PropertyIris.computerStorage).get(0).getIRI().getShortForm()));
        return new Desktop(individual.getIRI().getShortForm(),
                individual.getIRI().getShortForm(),
                Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri).get(0).getLiteral()),
                PlatformCompatibility.DESKTOP,
                motherboardConverter.convertFromOwlIndividuals(motherboard).get(0),
                gpuConverter.convertFromOwlIndividuals(gpu).get(0),
                this.powerSupplyConverter.convertFromOwlIndividuals(powerSupply).get(0),
                this.storageConverter.convertFromOwlIndividuals(storage).get(0)
        );
    }
}
