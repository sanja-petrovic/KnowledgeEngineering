package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.constants.PropertyIris;
import ftn.knowledge.engineering.ComputerRecommender.model.*;
import ftn.knowledge.engineering.ComputerRecommender.repository.OntologyRepository;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StorageConverterImpl implements StorageConverter {

    private final OntologyRepository repository;

    @Autowired
    public StorageConverterImpl(OntologyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Storage> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals) {
        List<Storage> converted = new ArrayList<>();
        for (OWLNamedIndividual individual : individuals) {
            Storage storage = new Storage();
            storage.setName(individual.getIRI().getShortForm());
            storage.setManufacturer(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.hardwareManufacturerIri).get(0).getLiteral());
            storage.setPriceEur(Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri).get(0).getLiteral()));
            storage.setType(StorageType.valueOf(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.storageTypeIri).get(0).getLiteral()));
            storage.setWriteSpeed(Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.writeSpeedIri).get(0).getLiteral()));
            storage.setMemoryCapacity(Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.memoryCapacityIri).get(0).getLiteral()));
            converted.add(storage);
        }
        return converted;
    }
}
