package ftn.knowledge.engineering.ComputerRecommender.repository;

import ftn.knowledge.engineering.ComputerRecommender.model.CPU;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.NodeSet;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OntologyRepository {
    OWLOntology loadFromFile(File file);
    OWLOntology loadFromUri(String uri);
    void saveToFile(File file);
    //NodeSet<OWLNamedIndividual> getIndividuals(OWLOntology ontology, String className);
    List<OWLNamedIndividual> getCpuIndividuals();
    List<OWLNamedIndividual> getRamIndividuals();
    List<OWLNamedIndividual> getRecommendedCpus(CPU properties);
    List<OWLLiteral> getDataPropertyValueOfIndividual(OWLNamedIndividual individual, String dataPropertyIri);
}
