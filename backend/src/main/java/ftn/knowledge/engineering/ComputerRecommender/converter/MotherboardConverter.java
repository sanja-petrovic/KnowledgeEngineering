package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.model.Motherboard;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface MotherboardConverter {
    List<Motherboard> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals);
}
