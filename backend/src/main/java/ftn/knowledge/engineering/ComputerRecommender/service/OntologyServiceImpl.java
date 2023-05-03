package ftn.knowledge.engineering.ComputerRecommender.service;

import ftn.knowledge.engineering.ComputerRecommender.constants.FilePaths;
import ftn.knowledge.engineering.ComputerRecommender.repository.OntologyRepository;
import org.semanticweb.owlapi.model.OWLOntology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class OntologyServiceImpl implements OntologyService {
    private final OntologyRepository repository;

    @Autowired
    public OntologyServiceImpl(OntologyRepository repository) {
        this.repository = repository;
    }


    @Override
    @PostConstruct
    public void test() {
        this.repository.loadFromFile(new File("C:\\Users\\Sanja\\Documents\\KnowledgeEngineering\\KT2\\ontologies\\ClassesPropertiesGeneral.rdf"));
        OWLOntology ontology = this.repository.loadFromFile(new File(FilePaths.ramIndividuals));
    }
}
