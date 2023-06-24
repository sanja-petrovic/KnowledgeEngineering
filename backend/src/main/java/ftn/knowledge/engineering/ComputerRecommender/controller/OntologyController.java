package ftn.knowledge.engineering.ComputerRecommender.controller;

import ftn.knowledge.engineering.ComputerRecommender.converter.ChipsetConverter;
import ftn.knowledge.engineering.ComputerRecommender.converter.DesktopConverter;
import ftn.knowledge.engineering.ComputerRecommender.converter.GPUConverter;

import ftn.knowledge.engineering.ComputerRecommender.model.*;
import ftn.knowledge.engineering.ComputerRecommender.service.ontology.OntologyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import ftn.knowledge.engineering.ComputerRecommender.converter.MotherboardConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "/ontology", tags = "Ontologies")
@RequestMapping(value = "/ontology")
public class OntologyController {
    private final OntologyService service;
    private final MotherboardConverter motherboardConverter;
    private final GPUConverter gpuConverter;
    private final ChipsetConverter chipsetConverter;
    private final DesktopConverter desktopConverter;
    @Autowired
    public OntologyController(OntologyService service, MotherboardConverter motherboardConverter, GPUConverter gpuConverter,
                              ChipsetConverter chipsetConverter, DesktopConverter desktopConverter) {
        this.service = service;
        this.motherboardConverter = motherboardConverter;
        this.gpuConverter = gpuConverter;
        this.chipsetConverter = chipsetConverter;
        this.desktopConverter = desktopConverter;
    }

    @GetMapping("/cpu/recommend")
    @ApiOperation(value = "Get recommended CPUs based on properties.", httpMethod = "GET")
    public ResponseEntity<List<String>> recommendCpus(
            @RequestParam(value = "clockSpeed", required = false) Double clockSpeed,
            @RequestParam(value = "coreCount", required = false) Integer coreCount,
            @RequestParam(value = "manufacturer", required = false) String manufacturer,
            @RequestParam(value = "priceMin", required = false) Double minimumPrice,
            @RequestParam(value = "priceMax", required = false) Double maximumPrice) {
        return ResponseEntity.ok(this.service.recommendCpus(clockSpeed != null ? clockSpeed : 0, coreCount != null ? coreCount : 0, manufacturer != null ? manufacturer : "", minimumPrice != null ? minimumPrice : 0, maximumPrice != null ? maximumPrice : Integer.MAX_VALUE));
    }

    @GetMapping("/ram/recommend")
    @ApiOperation(value = "Get recommended RAMs based on properties.", httpMethod = "GET")
    public ResponseEntity<List<String>> recommendRams(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "latency", required = false) Integer latency,
            @RequestParam(value = "frequency", required = false) Integer frequency,
            @RequestParam(value = "manufacturer", required = false) String manufacturer,
            @RequestParam(value = "priceMin", required = false) Double minimumPrice,
            @RequestParam(value = "priceMax", required = false) Double maximumPrice) {
        return ResponseEntity.ok(this.service.recommendRams(type != null ? type : "", size != null ? size : 0, latency != null ? latency : Integer.MAX_VALUE, frequency != null ? frequency : 0, manufacturer != null ? manufacturer : "", minimumPrice != null ? minimumPrice : 0, maximumPrice != null ? maximumPrice : Integer.MAX_VALUE));
    }
    @GetMapping("/chipset/recommend")
    @ApiOperation(value = "Get recommended Chipsets based on properties.", httpMethod = "GET")
    public ResponseEntity<List<String>> recommendChipsets(
            @RequestParam(value = "type", required = true) String type) {
        return ResponseEntity.ok(this.service.recommendChipsets(type));
    }
    @GetMapping("/motherboard/recommend")
    @ApiOperation(value = "Get recommended motherboards based on properties.", httpMethod = "GET")
    public ResponseEntity<List<String>> recommendMotherboards(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "manufacturer", required = false) String manufacturer,
            @RequestParam(value = "minRAM", required = false) Integer minRAM,
            @RequestParam(value = "maxRAM", required = false) Integer maxRAM,
            @RequestParam(value = "priceMin", required = false) Double minimumPrice,
            @RequestParam(value = "priceMax", required = false) Double maximumPrice) {

        return ResponseEntity.ok(this.service.recommendMotherboards(type != null ? type: "any",  maximumPrice != null ? maximumPrice : Integer.MAX_VALUE, minimumPrice != null ? minimumPrice : 0, manufacturer != null ? manufacturer:"any", minRAM != null ? minRAM : 0, maxRAM != null ? maxRAM : Integer.MAX_VALUE));
    }
    @GetMapping("/gpu/recommend")
    @ApiOperation(value = "Get recommended GPUs based on properties.", httpMethod = "GET")
    public ResponseEntity<List<String>> recommendGpus(
            @RequestParam(value = "manufacturer", required = false) String manufacturer,
            @RequestParam(value = "minVRAM", required = false) Integer minVRAM,
            @RequestParam(value = "maxVRAM", required = false) Integer maxVRAM,
            @RequestParam(value = "priceMin", required = false) Double minimumPrice,
            @RequestParam(value = "priceMax", required = false) Double maximumPrice,
            @RequestParam(value = "clockSpeedMin", required = false) Double clockMin,
            @RequestParam(value = "clockSpeedMax", required = false) Double clockMax) {
        return ResponseEntity.ok(this.service.recommendGPU( maximumPrice != null ? maximumPrice : Integer.MAX_VALUE, minimumPrice != null ? minimumPrice : 0, manufacturer != null ? manufacturer:"any", minVRAM != null ? minVRAM : 0, maxVRAM != null ? maxVRAM : Integer.MAX_VALUE, clockMin!=null?clockMin:0, clockMax!=null?clockMax:Integer.MAX_VALUE));
    }

    @GetMapping("/storage/recommend")
    @ApiOperation(value = "Get recommended storage based on properties.", httpMethod = "GET")
    public ResponseEntity<List<String>> recommendStorage(
            @RequestParam(value = "manufacturer", required = false) String manufacturer,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "memoryMin", required = false) Integer minMemoryCapacity,
            @RequestParam(value = "memoryMax", required = false) Integer maxMemoryCapacity,
            @RequestParam(value = "priceMin", required = false)  Double minPrice,
            @RequestParam(value = "priceMax", required = false)  Double maxPrice,
            @RequestParam(value = "writeSpeedMin", required = false)  Double writeSpeedMin)
    {
        return ResponseEntity.ok(this.service.recommendStorage(manufacturer != null ? manufacturer : "", type != null ? type : "", minMemoryCapacity != null ? minMemoryCapacity : 0, maxMemoryCapacity != null ? maxMemoryCapacity : Integer.MAX_VALUE, minPrice != null ? minPrice : 0, maxPrice != null ? maxPrice : Integer.MAX_VALUE, writeSpeedMin != null ? writeSpeedMin : 0));
    }
    @GetMapping("/powersupply/recommend")
    @ApiOperation(value = "Get recommended powersupply based on properties.", httpMethod = "GET")
    public ResponseEntity<List<String>> recommendPowerSupply(
            @RequestParam(value = "manufacturer", required = false) String manufacturer,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "wattage", required = false) Integer wattage,
            @RequestParam(value = "inputVoltageMin", required = false) Integer inputVoltageMin,
            @RequestParam(value = "inputVoltageMax", required = false) Integer inputVoltageMax,
            @RequestParam(value = "outputVoltage", required = false) Integer outputVoltage,
            @RequestParam(value = "inputAmperage", required = false)  Double inputAmperage,
            @RequestParam(value = "outputAmperage", required = false)  Double outputAmperage,
            @RequestParam(value = "priceMin", required = false)  Double minPrice,
            @RequestParam(value = "priceMax", required = false)  Double maxPrice)
    {
        return ResponseEntity.ok(this.service.recommendSPowerSupply(manufacturer != null ? manufacturer : "", type != null ? type : "", wattage != null ? wattage : 0, inputVoltageMin != null ? inputVoltageMin : Integer.MAX_VALUE, inputVoltageMax != null ? inputVoltageMax : Integer.MAX_VALUE, outputVoltage != null ? outputVoltage : Integer.MAX_VALUE, inputAmperage != null ? inputAmperage : Integer.MAX_VALUE, outputAmperage != null ? outputAmperage : Integer.MAX_VALUE, minPrice != null ? minPrice : 0, maxPrice != null ? maxPrice : Integer.MAX_VALUE));
    }

    @PutMapping("/cpu/upgrade")
    @ApiOperation(value = "Get suggested CPU upgrades.", httpMethod = "PUT")
    public ResponseEntity<?> upgradeCpus(@RequestBody Desktop desktop) {
        return ResponseEntity.ok(this.service.recommendCpuUpgrades(desktop));
    }

    @PutMapping("/ram/upgrade")
    @ApiOperation(value = "Get suggested RAM upgrades.", httpMethod = "PUT")
    public ResponseEntity<?> upgradeRams(@RequestBody Desktop desktop) {
        return ResponseEntity.ok(this.service.recommendRamUpgrades(desktop));
    }

    @GetMapping("/chipset/upgrade")
    @ApiOperation(value = "Get upgrades for chipset.", httpMethod = "GET")
    public ResponseEntity<?> getUpgradesChipset(
            @RequestParam(value = "type", required = true) String type,
            @RequestParam(value = "name", required = true) String name) {
        Chipset chipset = new Chipset();
        chipset.setType(ChipsetType.valueOf(type));
        chipset.setName(name);
        return ResponseEntity.ok(this.chipsetConverter.convertFromOwlIndividuals(this.service.upgradeChipset(chipset)));
    }

    @GetMapping("/gpu/upgrade")
    @ApiOperation(value = "Get upgrades for GPU.", httpMethod = "GET")
    public ResponseEntity<?> getUpgradesGPU(
            @RequestParam(value = "manufacturer", required = true) String manufacturer,
            @RequestParam(value = "boostClock", required = true) double boostClockSpeed,
            @RequestParam(value = "VRAMSize", required = true) int VRAM) {
        GPU gpu = new GPU();
        gpu.setManufacturer(manufacturer);
        gpu.setBoostClockSpeed(boostClockSpeed);
        gpu.setVRAMSize(VRAM);
        return ResponseEntity.ok(this.gpuConverter.convertFromOwlIndividuals(this.service.upgradeGPU(gpu)));
    }

    @GetMapping("/motherboard/upgrade")
    @ApiOperation(value = "Get upgrades for Motherboard.", httpMethod = "GET")
    public ResponseEntity<?> getUpgradesMotherboard(
            @RequestParam(value = "type", required = true) String motherboardType,
            @RequestParam(value = "numberOfRAMSlots", required = true) int numberOfRAMSlots) {
        Motherboard motherboard = new Motherboard();
        motherboard.setType(MotherboardType.valueOf(motherboardType));
        motherboard.setNumberOfRAMSlots(numberOfRAMSlots);
        List<OWLNamedIndividual> mbs = this.service.upgradeMotherboard(motherboard);
        List<Motherboard> mb = this.motherboardConverter.convertFromOwlIndividuals(mbs);
        return ResponseEntity.ok(mb);
    }
    @GetMapping("/desktop")
    @ApiOperation(value = "Get desktops ", httpMethod = "GET")
    public ResponseEntity<?> GetDesktops(){
        return ResponseEntity.ok(this.desktopConverter.convertFromOwlIndividuals(this.service.getDesktops()));
    }

}
