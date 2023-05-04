package ftn.knowledge.engineering.ComputerRecommender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GPU extends Base{
 private GPUType type;
 private double clockSpeed;
 private double boostClockSpeed;
 private int vRAMSize;

 public GPU(String name, String manufacturer, double priceEur, PlatformCompatibility platformCompatibility, GPUType type, double clockSpeed, double boostClockSpeed, int vRAMSize){
     super(name, manufacturer, priceEur, platformCompatibility);
     this. type = type;
     this.clockSpeed = clockSpeed;
     this. boostClockSpeed = boostClockSpeed;
     this. vRAMSize = vRAMSize;
 }
}
