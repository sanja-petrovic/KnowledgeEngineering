package ftn.knowledge.engineering.ComputerRecommender.repository;

import org.semanticweb.owlapi.model.OWLOntology;

import java.io.File;
import java.util.Optional;

public interface OntologyRepository {
    OWLOntology loadFromFile(File file);
    OWLOntology loadFromUri(String uri);
    void saveToFile(OWLOntology ontology, File file);
}
