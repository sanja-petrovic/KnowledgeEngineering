package ftn.knowledge.engineering.ComputerRecommender.repository;

import org.checkerframework.checker.units.qual.A;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.TurtleDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileOutputStream;

@Repository
public class OntologyRepositoryImpl implements OntologyRepository {
    private OWLOntologyManager manager;

    @Autowired
    public OntologyRepositoryImpl() {
        //
    }
    @Override
    public OWLOntology loadFromFile(File file) {
        this.initializeManager();
        try {
            return this.manager.loadOntologyFromOntologyDocument(file);
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OWLOntology loadFromUri(String uri) {
        this.initializeManager();
        try {
            return this.manager.loadOntology(IRI.create(uri));
        }  catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveToFile(OWLOntology ontology, File file) {
        this.initializeManager();
        try {
            this.manager.saveOntology(ontology, new TurtleDocumentFormat(), new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeManager() {
        if(this.manager == null) this.manager = OWLManager.createOWLOntologyManager();
    }
}
