package ftn.knowledge.engineering.ComputerRecommender.Converter;

import ftn.knowledge.engineering.ComputerRecommender.constants.PropertyIris;
import ftn.knowledge.engineering.ComputerRecommender.model.*;
import ftn.knowledge.engineering.ComputerRecommender.repository.OntologyRepository;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GPUConverterImpl implements GPUConverter{
    private final OntologyRepository repository;
    @Autowired
    public GPUConverterImpl(OntologyRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<GPU> ConvertFromOwlIndividuals(List<OWLNamedIndividual> individuals) {
        List<GPU> converted = new ArrayList<>();
        for(OWLNamedIndividual individual : individuals){
            GPU gpu = new GPU(individual.getIRI().getShortForm(),
                    this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.hardwareManufacturerIri).get(0).getLiteral(),
                    Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri).get(0).getLiteral()),
                    PlatformCompatibility.valueOf(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.platformCompatibilityIri).get(0).getLiteral()),
                    GPUType.valueOf(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.gpuTypeIri).get(0).getLiteral()),
                    Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.clockSpeedIri).get(0).getLiteral()),
                    Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.boostClockSpeedIri).get(0).getLiteral()),
                    Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.vRAMSizeIri).get(0).getLiteral())
            );
            converted.add(gpu);
        }
        return converted;
    }
}
