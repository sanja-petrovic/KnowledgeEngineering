package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.model.Storage;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface StorageConverter {
    List<Storage> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals);
}
