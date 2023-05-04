package ftn.knowledge.engineering.ComputerRecommender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chipset extends Base {
    private ChipsetType type;

    public Chipset(String name, String manufacturer, double priceEur, PlatformCompatibility platformCompatibility, ChipsetType type) {
        super(name, manufacturer, priceEur, platformCompatibility);
        this.type = type;
    }
}
