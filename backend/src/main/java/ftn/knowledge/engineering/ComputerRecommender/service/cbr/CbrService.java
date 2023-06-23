package ftn.knowledge.engineering.ComputerRecommender.service.cbr;

import es.ucm.fdi.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ftn.knowledge.engineering.ComputerRecommender.model.ComputerDescription;

import java.util.List;

public interface CbrService extends StandardCBRApplication {
    List<ComputerDescription> getDescriptions();

}
