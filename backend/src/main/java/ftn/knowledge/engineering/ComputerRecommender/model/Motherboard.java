package ftn.knowledge.engineering.ComputerRecommender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Motherboard extends Base {
    private int numberOfRAMSlots;
    private MotherboardType type;
    private Chipset chipset;
    private CPU cpu;
    private String ram;
    public Motherboard(String name, String manufacturer, double priceEur, PlatformCompatibility platformCompatibility, int numberOfRAMSlots, MotherboardType type, CPU cpu, Chipset chipset, String ram) {
        super(name, manufacturer, priceEur, platformCompatibility);
        this.numberOfRAMSlots = numberOfRAMSlots;
        this.type = type;
        this.cpu = cpu;
        this.chipset = chipset;
        this.ram = ram;
    }
}
