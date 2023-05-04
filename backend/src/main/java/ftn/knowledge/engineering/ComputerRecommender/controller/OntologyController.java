package ftn.knowledge.engineering.ComputerRecommender.controller;

import ftn.knowledge.engineering.ComputerRecommender.Converter.MotherboardConverter;
import ftn.knowledge.engineering.ComputerRecommender.dto.CpuDto;
import ftn.knowledge.engineering.ComputerRecommender.model.*;
import ftn.knowledge.engineering.ComputerRecommender.service.OntologyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
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
    @Autowired
    public OntologyController(OntologyService service, MotherboardConverter motherboardConverter) {
        this.service = service;
        this.motherboardConverter = motherboardConverter;
    }

    @GetMapping("/cpu/recommend")
    @ApiOperation(value = "Get recommended CPUs based on properties.", httpMethod = "GET")
    public ResponseEntity<?> recommendCpus(
            @RequestParam(value = "clockSpeed", required = false) Double clockSpeed,
            @RequestParam(value = "coreCount", required = false) Integer coreCount,
            @RequestParam(value = "manufacturer", required = false) String manufacturer,
            @RequestParam(value = "priceMin", required = false) Double minimumPrice,
            @RequestParam(value = "priceMax", required = false) Double maximumPrice) {
        return ResponseEntity.ok(this.service.recommendCpus(clockSpeed != null ? clockSpeed : 0, coreCount != null ? coreCount : 0, manufacturer != null ? manufacturer : "", minimumPrice != null ? minimumPrice : 0, maximumPrice != null ? maximumPrice : Integer.MAX_VALUE));
    }

    @GetMapping("/ram/recommend")
    @ApiOperation(value = "Get recommended RAMs based on properties.", httpMethod = "GET")
    public ResponseEntity<?> recommendRams(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "latency", required = false) Integer latency,
            @RequestParam(value = "frequency", required = false) Integer frequency,
            @RequestParam(value = "manufacturer", required = false) String manufacturer,
            @RequestParam(value = "priceMin", required = false) Double minimumPrice,
            @RequestParam(value = "priceMax", required = false) Double maximumPrice) {
        return ResponseEntity.ok(this.service.recommendRams(type != null ? type : "", size != null ? size : 0, latency != null ? latency : Integer.MAX_VALUE, frequency != null ? frequency : 0, manufacturer != null ? manufacturer : "", minimumPrice != null ? minimumPrice : 0, maximumPrice != null ? maximumPrice : Integer.MAX_VALUE));
    }

    @GetMapping("/cpu/{model}/upgrade")
    @ApiOperation(value = "Get suggested CPU upgrades.", httpMethod = "GET")
    public ResponseEntity<?> upgradeCpus(@PathVariable("model") String model) {
        return ResponseEntity.ok(this.service.recommendCpuUpgrades(model));
    }

    @GetMapping("/chipset/upgrade")
    @ApiOperation(value = "Get upgrades for chipset.", httpMethod = "GET")
    public ResponseEntity<?> getUpgradesChipset(
            @RequestParam(value = "chipset", required = true) Chipset chipset) {
        return ResponseEntity.ok(this.service.upgradeChipset(chipset));
    }
    @GetMapping("/gpu/upgrade")
    @ApiOperation(value = "Get upgrades for GPU.", httpMethod = "GET")
    public ResponseEntity<?> getUpgradesGPU(
            @RequestParam(value = "gpu", required = true) GPU gpu) {
        return ResponseEntity.ok(this.service.upgradeGPU(gpu));
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
        List<Motherboard> mb = this.motherboardConverter.ConvertFromOwlIndividuals(mbs);
        return ResponseEntity.ok(mb);
    }

}
