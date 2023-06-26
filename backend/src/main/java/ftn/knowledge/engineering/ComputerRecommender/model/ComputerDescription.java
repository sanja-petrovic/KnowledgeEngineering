package ftn.knowledge.engineering.ComputerRecommender.model;

import es.ucm.fdi.gaia.jcolibri.cbrcore.Attribute;
import es.ucm.fdi.gaia.jcolibri.cbrcore.CaseComponent;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ComputerDescription implements CaseComponent {
    private String name;
    private String manufacturer;
    private int releaseYear;
    private double cpuSpeedGhz;
    private int cpuCores;
    private int gpuSpeedMhz;
    private RAMType ramType;
    private int ramSizeGb;
    private int ramSpeedMhz;
    private int storageGb;

    @Override
    public Attribute getIdAttribute() {
        return new Attribute("name", ComputerDescription.class);
    }

    public ComputerDescription(String[] values) {
        this.name = values[0];
        this.manufacturer = values[1];
        this.releaseYear = Integer.parseInt(values[2]);
        this.cpuSpeedGhz = (Double.parseDouble(values[3]));
        this.cpuCores = Integer.parseInt(values[4]);
        this.gpuSpeedMhz = Integer.parseInt(values[5]);
        this.ramType = RAMType.valueOf(values[6].toUpperCase());
        this.ramSizeGb = Integer.parseInt(values[7]);
        this.ramSpeedMhz = Integer.parseInt(values[8]);
        this.storageGb = Integer.parseInt(values[9]);
    }
}
