package ftn.knowledge.engineering.ComputerRecommender.repository;

import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.NodeSet;

import java.io.File;
import java.util.List;

public interface OntologyRepository {
    OWLOntology loadFromFile(File file);
    OWLOntology loadFromUri(String uri);
    void saveToFile(OWLOntology ontology, File file);
    //NodeSet<OWLNamedIndividual> getIndividuals(OWLOntology ontology, String className);
    List<OWLNamedIndividual> getCpuIndividuals(OWLOntology ontology);
    List<OWLNamedIndividual> getRamIndividuals(OWLOntology ontology);
}
