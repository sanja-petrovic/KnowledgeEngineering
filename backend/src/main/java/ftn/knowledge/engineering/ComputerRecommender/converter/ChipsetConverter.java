package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.model.Chipset;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface ChipsetConverter {
    List<Chipset> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals);
}
