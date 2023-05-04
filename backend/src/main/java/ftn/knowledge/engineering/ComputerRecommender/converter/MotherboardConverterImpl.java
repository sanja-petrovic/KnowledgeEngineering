package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.constants.PropertyIris;
import ftn.knowledge.engineering.ComputerRecommender.model.Motherboard;
import ftn.knowledge.engineering.ComputerRecommender.model.MotherboardType;
import ftn.knowledge.engineering.ComputerRecommender.model.PlatformCompatibility;
import ftn.knowledge.engineering.ComputerRecommender.repository.OntologyRepository;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MotherboardConverterImpl implements MotherboardConverter{
    private final OntologyRepository repository;

    @Autowired
    public MotherboardConverterImpl(OntologyRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Motherboard> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals) {
        List<Motherboard> converted = new ArrayList<>();
        for(OWLNamedIndividual individual : individuals){
            Motherboard motherboard = new Motherboard(individual.getIRI().getShortForm(),
                    this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.hardwareManufacturerIri).get(0).getLiteral(),
                    Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri).get(0).getLiteral()),
                    PlatformCompatibility.DESKTOP,
                    Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.numberOfRAMSlotsIri).get(0).getLiteral()),
                    MotherboardType.valueOf(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.motherboardTypeIri).get(0).getLiteral())
                    );
            converted.add(motherboard);
        }
        return converted;
    }
}
