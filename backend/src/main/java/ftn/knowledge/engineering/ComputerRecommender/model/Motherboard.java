package ftn.knowledge.engineering.ComputerRecommender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Motherboard extends Base{
private int numberOfRAMSlots;
private MotherboardType type;

public Motherboard(String name, String manufacturer, double priceEur, PlatformCompatibility platformCompatibility, int numberOfRAMSlots,MotherboardType type){
    super(name, manufacturer, priceEur, platformCompatibility);
    this.numberOfRAMSlots = numberOfRAMSlots;
    this.type = type;
}
}
