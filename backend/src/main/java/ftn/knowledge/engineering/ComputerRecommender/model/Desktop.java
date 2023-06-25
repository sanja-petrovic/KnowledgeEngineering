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
    public Desktop(String name, String manufacturer, double priceEur, PlatformCompatibility platformCompatibility,Motherboard motherboard, GPU gpu){
        super(name,manufacturer,priceEur,platformCompatibility);
        this.motherboard = motherboard;
        this.gpu = gpu;

    }
}
