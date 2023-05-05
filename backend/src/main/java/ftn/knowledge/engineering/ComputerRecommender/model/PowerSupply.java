package ftn.knowledge.engineering.ComputerRecommender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PowerSupply extends Base {
    private PowerSupplyType type;
    private int wattage;
    private int inputVoltageMax;
    private int inputVoltageMin;
    private int outputVoltage;
    private double inputAmperage;
    private double outputAmperage;

}
