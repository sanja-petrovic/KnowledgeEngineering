package ftn.knowledge.engineering.ComputerRecommender.repository.cbr;

import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseBaseFilter;
import es.ucm.fdi.gaia.jcolibri.exception.InitializingException;
import es.ucm.fdi.gaia.jcolibri.util.FileIO;
import ftn.knowledge.engineering.ComputerRecommender.model.ComputerDescription;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static ftn.knowledge.engineering.ComputerRecommender.constants.FilePaths.caseBase;

@Repository
public class CbrRepositoryImpl implements CbrRepository {

    private List<ComputerDescription> descriptions;
    private final Collection<CBRCase> cases;

    public CbrRepositoryImpl() {
        this.descriptions = new ArrayList<>();
        this.cases = retrieveAllCases();
    }

    public Collection<CBRCase> getCases() {
        return this.cases;
    }

    public List<ComputerDescription> getDescriptions() {
        return this.descriptions;
    }

    @Override
    public Collection<CBRCase> retrieveAllCases() {
        LinkedList<CBRCase> cases = new LinkedList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile(caseBase)));

            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || (line.length() == 0))
                    continue;
                String[] values = line.split(";");

                CBRCase cbrCase = new CBRCase();

                ComputerDescription description = new ComputerDescription(values);
                this.descriptions.add(description);
                cbrCase.setDescription(description);
                cases.add(cbrCase);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cases;
    }

    @Override
    public void close() {
        throw new NotImplementedException();
    }

    @Override
    public void storeCases(Collection<CBRCase> collection) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteCases(Collection<CBRCase> collection) {
        throw new NotImplementedException();
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter caseBaseFilter) {
        throw new NotImplementedException();
    }

    @Override
    public void initFromXMLfile(URL url) throws InitializingException {
        throw new NotImplementedException();
    }
}
