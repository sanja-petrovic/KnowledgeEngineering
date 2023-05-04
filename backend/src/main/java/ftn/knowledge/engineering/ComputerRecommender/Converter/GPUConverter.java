package ftn.knowledge.engineering.ComputerRecommender.Converter;

import ftn.knowledge.engineering.ComputerRecommender.model.GPU;
import ftn.knowledge.engineering.ComputerRecommender.model.Motherboard;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface GPUConverter {
    List<GPU> ConvertFromOwlIndividuals(List<OWLNamedIndividual> individuals);
}
