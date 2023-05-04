package ftn.knowledge.engineering.ComputerRecommender.repository;

import ftn.knowledge.engineering.ComputerRecommender.constants.ClassIris;
import ftn.knowledge.engineering.ComputerRecommender.constants.FilePaths;
import org.eclipse.rdf4j.model.vocabulary.OWL;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.TurtleDocumentFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OntologyRepositoryImpl implements OntologyRepository {
    private OWLOntologyManager manager;
    private OWLOntology ontology;

    public OntologyRepositoryImpl() {
        this.manager = OWLManager.createOWLOntologyManager();
        try {
            this.ontology = this.manager.loadOntologyFromOntologyDocument(new File(FilePaths.knowledgeBase));
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OWLOntology loadFromFile(File file) {
        try {
            return this.manager.loadOntologyFromOntologyDocument(file);
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OWLOntology loadFromUri(String uri) {
        try {
            return this.manager.loadOntology(IRI.create(uri));
        }  catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveToFile(File file) {
        try {
            this.manager.saveOntology(this.ontology, new TurtleDocumentFormat(), new FileOutputStream(file));
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
    public List<OWLNamedIndividual> getCpuIndividuals() {
        OWLDataFactory df = this.manager.getOWLDataFactory();
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(this.ontology);
        OWLClass cpuClass = df.getOWLClass(ClassIris.cpuIri);
        List<OWLNamedIndividual> cpuIndividuals = new ArrayList<>();
        for (OWLClassExpression cpuType : EntitySearcher.getSubClasses(cpuClass, this.ontology).toList()) {
            for(OWLClassExpression cpuSubClass : EntitySearcher.getSubClasses(cpuType.asOWLClass(), this.ontology).toList()) {
                var instances = reasoner.getInstances(cpuSubClass.asOWLClass(), true);
                cpuIndividuals.addAll(instances.getFlattened());
            }

        }

        return cpuIndividuals;
    }

    @Override
    public List<OWLNamedIndividual> getRamIndividuals() {
        OWLDataFactory df = this.manager.getOWLDataFactory();
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(this.ontology);
        OWLClass ramClass = df.getOWLClass(ClassIris.ramIri);
        List<OWLNamedIndividual> ramIndividuals = new ArrayList<>();
        for (OWLClassExpression ramType : EntitySearcher.getSubClasses(ramClass, this.ontology).toList()) {
            var instances = reasoner.getInstances(ramType.asOWLClass(), true);
            ramIndividuals.addAll(instances.getFlattened());
        }

        return ramIndividuals;
    }

    @Override
    public List<OWLNamedIndividual> getRecommendedCpus() {
        return null;
    }
}
