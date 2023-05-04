package ftn.knowledge.engineering.ComputerRecommender.service;

import ftn.knowledge.engineering.ComputerRecommender.constants.FilePaths;
import ftn.knowledge.engineering.ComputerRecommender.constants.PropertyIris;
import ftn.knowledge.engineering.ComputerRecommender.dto.CpuDto;
import ftn.knowledge.engineering.ComputerRecommender.model.CPU;
import ftn.knowledge.engineering.ComputerRecommender.repository.OntologyRepository;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        //this.repository.loadFromFile(new File("C:\\Users\\Sanja\\Documents\\KnowledgeEngineering\\KT2\\ontologies\\ClassesPropertiesGeneral.rdf"));
        repository.getRamIndividuals();
    }

    @Override
    public void recommendCpus(CpuDto dto) {
        List<OWLNamedIndividual> cpuIndividuals = repository.getCpuIndividuals();
        for (OWLNamedIndividual individual : cpuIndividuals) {
        }
    }

    @Override
    public List<String> recommendCpus(Double clockSpeed, Integer coreCount, String manufacturer, Double minimumPrice, Double maximumPrice) {
        List<OWLNamedIndividual> cpuIndividuals = repository.getCpuIndividuals();
        List<String> recommendations = new ArrayList<>();
        for (OWLNamedIndividual individual : cpuIndividuals) {
            var cs = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.clockSpeedIri);
            double individualClockSpeed = cs.size() > 0 ? Double.parseDouble(cs.get(0).getLiteral()) : 0.0;

            var cc = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.coreCountIri);
            int individualCoreCount = cc.size() > 0 ? Integer.parseInt(cc.get(0).getLiteral()) : 0;

            var m = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.hardwareManufacturerIri);
            String individualManufacturer = m.size() > 0 ? m.get(0).getLiteral() : "";

            var p = this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri);
            double individualPrice = p.size() > 0 ? Double.parseDouble(p.get(0).getLiteral()) : 0;

            if (individualCoreCount >= coreCount && individualClockSpeed >= clockSpeed && individualManufacturer.contains(manufacturer) && individualPrice >= minimumPrice && individualPrice <= maximumPrice) {
                recommendations.add(individual.getIRI().getShortForm());
            }

        }

        return recommendations;
    }
}
