package ftn.knowledge.engineering.ComputerRecommender.repository;

import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import java.io.File;
import java.util.List;

public interface OntologyRepository {
    OWLOntology loadFromFile(File file);

    OWLOntology loadFromUri(String uri);

    void saveToFile(File file);

    List<OWLNamedIndividual> getCpuIndividuals();
    List<OWLNamedIndividual> getDesktopIndividuals();
    List<OWLNamedIndividual> getRamIndividuals();

    List<OWLNamedIndividual> getChipsetIndividuals();

    List<OWLNamedIndividual> getMotherboardIndividuals();

    List<OWLNamedIndividual> getGPUIndividuals();

    List<OWLNamedIndividual> getStorageIndividuals();

    List<OWLNamedIndividual> getPowerSupplyIndividuals();

    List<OWLLiteral> getDataPropertyValueOfIndividual(OWLNamedIndividual individual, String dataPropertyIri);
     List<OWLNamedIndividual> getObjectPropertyValueOfIndividual(OWLNamedIndividual individual, String objectPropertyIri);

     List<OWLNamedIndividual> getObjectPropertyValueOfChipsetFromSuperclass(OWLNamedIndividual individual);
    List<OWLNamedIndividual> getObjectPropertyValueOfRAMFromSuperclass(OWLNamedIndividual individual);
    List<OWLNamedIndividual> getObjectPropertyValueOfChipsetsForCpusFromSuperclass(OWLNamedIndividual individual);
}
