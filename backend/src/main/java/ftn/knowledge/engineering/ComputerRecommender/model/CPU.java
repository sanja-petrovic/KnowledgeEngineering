package ftn.knowledge.engineering.ComputerRecommender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CPU extends Base {
    private String generation;
    private double clockSpeed;
    private int coreCount;
    private int threadCount;
    private int tdp;
    private List<Double> cacheSizes = new ArrayList<>();
    private boolean supportsMultithreading;
    private boolean supportsOverclocking;
    private boolean hasIntegratedGraphics;
    private List<Chipset> compatibleChipsets;
    public CPU(String name, String manufacturer, double priceEur, PlatformCompatibility platformCompatibility, String generation, double clockSpeed, int coreCount, int threadCount, int tdp, List<Double> cacheSizes, boolean supportsMultithreading, boolean supportsOverclocking, boolean hasIntegratedGraphics, List<Chipset>compatibleChipsets) {
        super(name, manufacturer, priceEur, platformCompatibility);
        this.generation = generation;
        this.clockSpeed = clockSpeed;
        this.coreCount = coreCount;
        this.threadCount = threadCount;
        this.tdp = tdp;
        this.cacheSizes = cacheSizes;
        this.supportsMultithreading = supportsMultithreading;
        this.supportsOverclocking = supportsOverclocking;
        this.hasIntegratedGraphics = hasIntegratedGraphics;
        this.compatibleChipsets = compatibleChipsets;
    }
}
