package ftn.knowledge.engineering.ComputerRecommender.repository.cbr;

import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.Connector;
import ftn.knowledge.engineering.ComputerRecommender.model.ComputerDescription;

import java.util.Collection;
import java.util.List;

public interface CbrRepository extends Connector {
    Collection<CBRCase> getCases();
    List<ComputerDescription> getDescriptions();
}
