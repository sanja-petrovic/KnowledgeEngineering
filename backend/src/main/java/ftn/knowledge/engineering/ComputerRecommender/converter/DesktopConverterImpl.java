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
    public DesktopConverterImpl(OntologyService service, GPUConverter gpuConverter, MotherboardConverter motherboardConverter, OntologyRepository repository){
        this.service = service;
        this.gpuConverter = gpuConverter;
        this.motherboardConverter = motherboardConverter;
        this.repository = repository;
    }

    @Override
    public List<Desktop> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals) {
        List<Desktop> converted = new ArrayList<>();
        for(OWLNamedIndividual individual : individuals){
            List<OWLNamedIndividual> motherboard = new ArrayList<>();
            motherboard.add(this.service.getMotherboardByName(this.repository.getObjectPropertyValueOfIndividual(individual, PropertyIris.computerMotherboardIri).get(0).getIRI().getShortForm()));
            List<OWLNamedIndividual> gpu = new ArrayList<>();
            gpu.add(this.service.getGPUByName(this.repository.getObjectPropertyValueOfIndividual(individual, PropertyIris.computerGpuIri).get(0).getIRI().getShortForm()));
            Desktop desktop = new Desktop(individual.getIRI().getShortForm(),
                    individual.getIRI().getShortForm(),
                    Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri).get(0).getLiteral()),
                    PlatformCompatibility.DESKTOP,
                    motherboardConverter.convertFromOwlIndividuals(motherboard).get(0),
                    gpuConverter.convertFromOwlIndividuals(gpu).get(0)
            );
            converted.add(desktop);
        }
        return converted;
    }
}
