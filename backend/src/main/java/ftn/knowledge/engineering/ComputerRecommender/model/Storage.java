package ftn.knowledge.engineering.ComputerRecommender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Storage extends Base{
    private StorageType type;
    private double writeSpeed;
    private int memoryCapacity;

    public Storage(String name, String manufacturer, double priceEur, StorageType type, double writeSpeed, int memoryCapacity){
        super(name, manufacturer, priceEur, null);
        this.type = type;
        this.writeSpeed = writeSpeed;
        this.memoryCapacity = memoryCapacity;
    }
}
