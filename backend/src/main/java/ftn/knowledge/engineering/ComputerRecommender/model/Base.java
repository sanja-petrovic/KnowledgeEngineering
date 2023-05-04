package ftn.knowledge.engineering.ComputerRecommender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Base {
    private String name;
    private String manufacturer;
    private double priceEur;
    private PlatformCompatibility platformCompatibility;
}
