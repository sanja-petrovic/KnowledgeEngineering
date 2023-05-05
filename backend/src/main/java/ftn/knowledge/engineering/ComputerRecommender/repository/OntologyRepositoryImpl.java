package ftn.knowledge.engineering.ComputerRecommender.repository;

import ftn.knowledge.engineering.ComputerRecommender.constants.ClassIris;
import ftn.knowledge.engineering.ComputerRecommender.constants.FilePaths;
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
    private final OWLOntologyManager manager;
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
        } catch (OWLOntologyCreationException e) {
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

    @Override
    public List<OWLNamedIndividual> getCpuIndividuals() {
        OWLDataFactory df = this.manager.getOWLDataFactory();
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(this.ontology);
        OWLClass cpuClass = df.getOWLClass(ClassIris.cpuIri);
        List<OWLNamedIndividual> cpuIndividuals = new ArrayList<>();
        for (OWLClassExpression cpuType : EntitySearcher.getSubClasses(cpuClass, this.ontology).toList()) {
            for (OWLClassExpression cpuSubClass : EntitySearcher.getSubClasses(cpuType.asOWLClass(), this.ontology).toList()) {
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

    public List<OWLLiteral> getDataPropertyValueOfIndividual(OWLNamedIndividual individual, String dataPropertyIri) {
        return EntitySearcher.getDataPropertyValues(individual, this.manager.getOWLDataFactory().getOWLDataProperty(dataPropertyIri), this.ontology).toList();
    }

    @Override
    public List<OWLNamedIndividual> getChipsetIndividuals() {
        OWLDataFactory df = this.manager.getOWLDataFactory();
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(this.ontology);
        OWLClass chipsetClass = df.getOWLClass(ClassIris.chipsetIri);
        List<OWLNamedIndividual> chipsetIndividuals = new ArrayList<>();
        for (OWLClassExpression chipsetType : EntitySearcher.getSubClasses(chipsetClass, this.ontology).toList()) {
            for (OWLClassExpression chipsetSubClass : EntitySearcher.getSubClasses(chipsetType.asOWLClass(), this.ontology).toList()) {
                var instances = reasoner.getInstances(chipsetSubClass.asOWLClass(), true);
                chipsetIndividuals.addAll(instances.getFlattened());
            }
        }

        return chipsetIndividuals;
    }

    @Override
    public List<OWLNamedIndividual> getMotherboardIndividuals() {
        OWLDataFactory df = this.manager.getOWLDataFactory();
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(this.ontology);
        OWLClass motherboardClass = df.getOWLClass(ClassIris.motherboardIri);
        List<OWLNamedIndividual> motherboardIndividuals = new ArrayList<>();
        for (OWLClassExpression motherboardType : EntitySearcher.getSubClasses(motherboardClass, this.ontology).toList()) {
            for (OWLClassExpression motherboardSubClass : EntitySearcher.getSubClasses(motherboardType.asOWLClass(), this.ontology).toList()) {
                var instances = reasoner.getInstances(motherboardSubClass.asOWLClass(), true);
                motherboardIndividuals.addAll(instances.getFlattened());
            }
        }

        return motherboardIndividuals;
    }

    @Override
    public List<OWLNamedIndividual> getGPUIndividuals() {
        OWLDataFactory df = this.manager.getOWLDataFactory();
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(this.ontology);
        OWLClass gpuClass = df.getOWLClass(ClassIris.gpuIri);
        List<OWLNamedIndividual> gpuIndividuals = new ArrayList<>();
        for (OWLClassExpression gpuType : EntitySearcher.getSubClasses(gpuClass, this.ontology).toList()) {

            var instances = reasoner.getInstances(gpuType.asOWLClass(), true);
            gpuIndividuals.addAll(instances.getFlattened());

        }
        return gpuIndividuals;
    }
    @Override
    public List<OWLNamedIndividual> getStorageIndividuals(){

        OWLDataFactory df = this.manager.getOWLDataFactory();
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(this.ontology);
        OWLClass storageClass = df.getOWLClass(ClassIris.storageIri);

        List<OWLNamedIndividual> storageIndividuals = new ArrayList<>();
        for (OWLClassExpression storageType : EntitySearcher.getSubClasses(storageClass, this.ontology).toList()) {
            for (OWLClassExpression storageSubClass : EntitySearcher.getSubClasses(storageType.asOWLClass(), this.ontology).toList()) {
                var instances = reasoner.getInstances(storageSubClass.asOWLClass(), true);
                storageIndividuals.addAll(instances.getFlattened());
            }
        }
        return storageIndividuals;
    }
}
