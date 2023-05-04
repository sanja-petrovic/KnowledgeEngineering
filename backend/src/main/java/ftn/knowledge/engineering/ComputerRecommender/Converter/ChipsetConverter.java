package ftn.knowledge.engineering.ComputerRecommender.Converter;

import ftn.knowledge.engineering.ComputerRecommender.model.Chipset;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface ChipsetConverter {
    List<Chipset> ConvertFromOwlIndividuals(List<OWLNamedIndividual> individuals);
}
