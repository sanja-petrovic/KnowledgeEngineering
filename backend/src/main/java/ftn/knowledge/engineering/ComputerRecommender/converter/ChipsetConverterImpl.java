package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.constants.PropertyIris;
import ftn.knowledge.engineering.ComputerRecommender.model.Chipset;
import ftn.knowledge.engineering.ComputerRecommender.model.ChipsetType;
import ftn.knowledge.engineering.ComputerRecommender.repository.OntologyRepository;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChipsetConverterImpl implements ChipsetConverter {
    private final OntologyRepository repository;

    @Autowired
    public ChipsetConverterImpl(OntologyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Chipset> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals) {
        List<Chipset> converted = new ArrayList<>();
        for (OWLNamedIndividual individual : individuals) {
            Chipset chipset = new Chipset();
            chipset.setType(ChipsetType.valueOf(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.chipsetTypeIri).get(0).getLiteral()));
            chipset.setName(individual.getIRI().getShortForm());
            converted.add(chipset);
        }
        return converted;
    }
}
