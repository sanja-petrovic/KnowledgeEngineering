package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.model.Motherboard;
import ftn.knowledge.engineering.ComputerRecommender.model.PowerSupply;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface PowerSupplyConverter {
    List<PowerSupply> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals);

}
