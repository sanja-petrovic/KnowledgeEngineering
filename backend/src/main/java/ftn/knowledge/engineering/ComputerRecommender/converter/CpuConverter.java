package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.model.CPU;
import ftn.knowledge.engineering.ComputerRecommender.model.GPU;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface CpuConverter {
    List<CPU> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals);
}
