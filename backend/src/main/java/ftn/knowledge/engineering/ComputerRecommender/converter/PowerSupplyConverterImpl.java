package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.constants.PropertyIris;
import ftn.knowledge.engineering.ComputerRecommender.model.PowerSupply;
import ftn.knowledge.engineering.ComputerRecommender.model.PowerSupplyType;
import ftn.knowledge.engineering.ComputerRecommender.model.StorageType;
import ftn.knowledge.engineering.ComputerRecommender.repository.ontology.OntologyRepository;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PowerSupplyConverterImpl implements PowerSupplyConverter {
    private final OntologyRepository repository;

    public PowerSupplyConverterImpl(OntologyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PowerSupply> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals) {
        List<PowerSupply> converted = new ArrayList<>();
        for (OWLNamedIndividual individual : individuals) {
            PowerSupply powerSupply = new PowerSupply();
            powerSupply.setName(individual.getIRI().getShortForm());
            powerSupply.setType(PowerSupplyType.valueOf(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.powerSupplyTypeIri).get(0).getLiteral()));
            powerSupply.setManufacturer(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.hardwareManufacturerIri).get(0).getLiteral());
            powerSupply.setPriceEur(Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri).get(0).getLiteral()));
            powerSupply.setWattage(Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.wattageIri).get(0).getLiteral()));
            //powerSupply.setInputAmperage(Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.inputAmperageIri).get(0).getLiteral()));
            //powerSupply.setOutputAmperage(Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.outputAmperageIri).get(0).getLiteral()));
            //powerSupply.setInputVoltageMax(Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.inputVoltageMaxIri).get(0).getLiteral()));
            //powerSupply.setInputVoltageMin(Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.inputVoltageMinIri).get(0).getLiteral()));
            //powerSupply.setOutputVoltage(Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.outputVoltageIri).get(0).getLiteral()));
            converted.add(powerSupply);
        }
        return converted;
    }
}
