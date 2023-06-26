package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.constants.PropertyIris;
import ftn.knowledge.engineering.ComputerRecommender.model.GPU;
import ftn.knowledge.engineering.ComputerRecommender.model.GPUType;
import ftn.knowledge.engineering.ComputerRecommender.model.PlatformCompatibility;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

public interface GPUConverter {
    List<GPU> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals);
}
