package ftn.knowledge.engineering.ComputerRecommender.repository.cbr;

import es.ucm.fdi.gaia.jcolibri.cbrcore.CBRCase;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseBaseFilter;
import es.ucm.fdi.gaia.jcolibri.exception.InitializingException;
import es.ucm.fdi.gaia.jcolibri.util.FileIO;
import ftn.knowledge.engineering.ComputerRecommender.model.ComputerDescription;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ftn.knowledge.engineering.ComputerRecommender.constants.FilePaths.caseBase;

@Repository
public class CbrRepositoryImpl implements CbrRepository {

    private final Collection<CBRCase> cases;
    private final List<ComputerDescription> descriptions;

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

    public ComputerDescription getDescription(String name) {
        for (ComputerDescription computerDescription : this.descriptions) {
            if (name.equalsIgnoreCase(computerDescription.getName())) {
                return computerDescription;
            }
        }

        throw new EntityNotFoundException();
    }

    @Override
    public void save(ComputerDescription computerDescription) {
        List<String[]> dataLines = new ArrayList<>();
        dataLines.add(new String[]{
                computerDescription.getName(),
                computerDescription.getManufacturer(),
                Integer.toString(computerDescription.getReleaseYear()),
                Double.toString(computerDescription.getCpuSpeedGhz()),
                Integer.toString(computerDescription.getCpuCores()),
                Integer.toString(computerDescription.getGpuSpeedMhz()),
                computerDescription.getRamType().toString(),
                Integer.toString(computerDescription.getRamSizeGb()),
                Integer.toString(computerDescription.getRamSpeedMhz()),
                Integer.toString(computerDescription.getStorageGb())
        });
        File file = new File(caseBase);
        try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
            pw.println("");
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::print);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(";"));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
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
                if (!descriptions.contains(description)) {
                    this.descriptions.add(description);
                }
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
