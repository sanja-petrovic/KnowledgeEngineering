package ftn.knowledge.engineering.ComputerRecommender.Converter;

import ftn.knowledge.engineering.ComputerRecommender.model.Motherboard;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface MotherboardConverter {
    List<Motherboard> ConvertFromOwlIndividuals(List<OWLNamedIndividual> individuals);
}
