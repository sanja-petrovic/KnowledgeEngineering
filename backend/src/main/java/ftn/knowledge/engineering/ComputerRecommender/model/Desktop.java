package ftn.knowledge.engineering.ComputerRecommender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Desktop extends Base {
    private Motherboard motherboard;
    private GPU gpu;
    private PowerSupply powerSupply;
    private Storage storage;
    public Desktop(String name, String manufacturer, double priceEur, PlatformCompatibility platformCompatibility,Motherboard motherboard, GPU gpu, PowerSupply powerSupply, Storage storage){
        super(name,manufacturer,priceEur,platformCompatibility);
        this.motherboard = motherboard;
        this.gpu = gpu;
        this.powerSupply = powerSupply;
        this.storage = storage;

    }
}
