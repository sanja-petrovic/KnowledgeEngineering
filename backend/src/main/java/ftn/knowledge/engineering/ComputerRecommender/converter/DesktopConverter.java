package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.model.Desktop;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface DesktopConverter {
    List<Desktop> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals);
    Desktop convertFromOwlIndividual(OWLNamedIndividual individual);
}
