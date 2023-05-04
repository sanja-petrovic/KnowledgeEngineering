package ftn.knowledge.engineering.ComputerRecommender.controller;

import ftn.knowledge.engineering.ComputerRecommender.dto.CpuDto;
import ftn.knowledge.engineering.ComputerRecommender.model.CPU;
import ftn.knowledge.engineering.ComputerRecommender.service.OntologyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "/ontology", tags = "Ontologies")
@RequestMapping(value = "/ontology")
public class OntologyController {
    private final OntologyService service;

    @Autowired
    public OntologyController(OntologyService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Test.", httpMethod = "GET")
    public ResponseEntity<?> test() {
        this.service.test();
        return ResponseEntity.ok().body("Test succeeded");
    }

    @GetMapping("/cpu/recommend")
    @ApiOperation(value = "Get recommended CPUs based on properties.", httpMethod = "GET")
    public ResponseEntity<?> recommendCpus(@RequestParam(value = "clockSpeed", required = false) Double clockSpeed,
                                           @RequestParam(value = "coreCount", required = false) Integer coreCount,
                                           @RequestParam(value = "manufacturer", required = false) String manufacturer,
                                           @RequestParam(value = "priceMin", required = false) Double minimumPrice,
                                           @RequestParam(value = "priceMax", required = false) Double maximumPrice) {
        return ResponseEntity.ok(
                this.service.recommendCpus(
                        clockSpeed != null ? clockSpeed : 0,
                        coreCount != null ? coreCount : 0,
                        manufacturer != null ? manufacturer : "",
                        minimumPrice != null ? minimumPrice : 0,
                        maximumPrice != null ? maximumPrice : Integer.MAX_VALUE
                )
        );
    }



}
