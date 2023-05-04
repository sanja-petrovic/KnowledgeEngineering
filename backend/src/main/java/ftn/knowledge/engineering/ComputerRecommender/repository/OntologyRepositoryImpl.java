package ftn.knowledge.engineering.ComputerRecommender.repository;

import ftn.knowledge.engineering.ComputerRecommender.constants.ClassIris;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.TurtleDocumentFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OntologyRepositoryImpl implements OntologyRepository {
    private OWLOntologyManager manager;

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

    /*@Override
    public NodeSet<OWLNamedIndividual> getIndividuals(OWLOntology ontology, String className) {
        OWLDataFactory df = this.manager.getOWLDataFactory();
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
        OWLClass owlClass = df.getOWLClass("http://www.semanticweb.org/bogdan/ontologies/2023/3/untitled-ontology-2#CPU");
        for (OWLClassExpression a : EntitySearcher.getSubClasses(owlClass, ontology).toList()) {
            OWLClass cls = a.asOWLClass();
            if (cls.getIRI().getFragment().equalsIgnoreCase(className)) {
                System.out.println("My class is : " + cls.getIRI().getShortForm());
                System.out.println("The IRI of my class is : " + cls);
                System.out.println("-----------------------");

                NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cls, true);
                System.out.println("The Individuals of my class : ");

                for (OWLNamedIndividual i : instances.getFlattened()) {
                    System.out.println(i.getIRI().getFragment());
                }

                return instances;
            }
        }

        return null;
    }*/

    @Override
    public List<OWLNamedIndividual> getCpuIndividuals(OWLOntology ontology) {
        OWLDataFactory df = this.manager.getOWLDataFactory();
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
        OWLClass cpuClass = df.getOWLClass(ClassIris.cpuIri);
        List<OWLNamedIndividual> cpuIndividuals = new ArrayList<>();
        for (OWLClassExpression cpuType : EntitySearcher.getSubClasses(cpuClass, ontology).toList()) {
            for(OWLClassExpression cpuSubClass : EntitySearcher.getSubClasses(cpuType.asOWLClass(), ontology).toList()) {
                var instances = reasoner.getInstances(cpuSubClass.asOWLClass(), true);
                cpuIndividuals.addAll(instances.getFlattened());
            }

        }

        return cpuIndividuals;
    }

    @Override
    public List<OWLNamedIndividual> getRamIndividuals(OWLOntology ontology) {
        OWLDataFactory df = this.manager.getOWLDataFactory();
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
        OWLClass ramClass = df.getOWLClass(ClassIris.ramIri);
        List<OWLNamedIndividual> ramIndividuals = new ArrayList<>();
        for (OWLClassExpression ramType : EntitySearcher.getSubClasses(ramClass, ontology).toList()) {
            var instances = reasoner.getInstances(ramType.asOWLClass(), true);
            ramIndividuals.addAll(instances.getFlattened());
        }

        return ramIndividuals;
    }

    private void initializeManager() {
        if(this.manager == null) this.manager = OWLManager.createOWLOntologyManager();
    }
}
