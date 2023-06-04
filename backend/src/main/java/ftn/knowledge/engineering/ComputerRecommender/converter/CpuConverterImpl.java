package ftn.knowledge.engineering.ComputerRecommender.converter;

import ftn.knowledge.engineering.ComputerRecommender.constants.PropertyIris;
import ftn.knowledge.engineering.ComputerRecommender.model.CPU;
import ftn.knowledge.engineering.ComputerRecommender.model.PlatformCompatibility;
import ftn.knowledge.engineering.ComputerRecommender.repository.OntologyRepository;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CpuConverterImpl implements  CpuConverter{
    private final OntologyRepository repository;
    private final ChipsetConverter converter;
    public CpuConverterImpl(OntologyRepository repository, ChipsetConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<CPU> convertFromOwlIndividuals(List<OWLNamedIndividual> individuals) {
        List<CPU> converted = new ArrayList<>();
        for (OWLNamedIndividual individual : individuals) {
            CPU cpu = new CPU(individual.getIRI().getShortForm(),
                    this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.hardwareManufacturerIri).get(0).getLiteral(),
                    Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.priceIri).get(0).getLiteral()),
                    PlatformCompatibility.valueOf(this.repository.getDataPropertyValueOfIndividual(individual,
                            PropertyIris.platformCompatibilityIri).get(0).getLiteral()),
                   this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.generationIri).get(0).getLiteral()
                    , Double.parseDouble(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.clockSpeedIri).get(0).getLiteral()),
                    Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.coreCountIri).get(0).getLiteral()),
                    Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.threadCountIri).get(0).getLiteral()),
                    //Integer.parseInt(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.tdpIri).get(0).getLiteral()
                    0, new ArrayList<>(),
                    Boolean.valueOf(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.multithreadIri).get(0).getLiteral()),
                    Boolean.valueOf(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.overclockIri).get(0).getLiteral()),
                    Boolean.valueOf(this.repository.getDataPropertyValueOfIndividual(individual, PropertyIris.integratedGraphicsIri).get(0).getLiteral()),
                    this.converter.convertFromOwlIndividuals(this.repository.getObjectPropertyValueOfChipsetsForCpusFromSuperclass(individual))
                    );
            converted.add(cpu);
        }
        return converted;
    }
}
