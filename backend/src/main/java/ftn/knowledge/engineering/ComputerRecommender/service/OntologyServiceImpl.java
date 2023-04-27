package ftn.knowledge.engineering.ComputerRecommender.service;

import ftn.knowledge.engineering.ComputerRecommender.repository.OntologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OntologyServiceImpl implements OntologyService {
    private final OntologyRepository repository;

    @Autowired
    public OntologyServiceImpl(OntologyRepository repository) {
        this.repository = repository;
    }
}
