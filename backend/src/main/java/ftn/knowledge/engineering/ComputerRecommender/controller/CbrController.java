package ftn.knowledge.engineering.ComputerRecommender.controller;

import ftn.knowledge.engineering.ComputerRecommender.dto.CbrInput;
import ftn.knowledge.engineering.ComputerRecommender.dto.CbrOutput;
import ftn.knowledge.engineering.ComputerRecommender.model.ComputerDescription;
import ftn.knowledge.engineering.ComputerRecommender.service.cbr.CbrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "/cbr", tags = "Case-based Reasoning")
@RequestMapping(value = "/cbr")
public class CbrController {
    private final CbrService service;

    public CbrController(CbrService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Get all computers in case base.")
    public ResponseEntity<List<ComputerDescription>> getDescriptions() {
        return ResponseEntity.ok(service.getDescriptions());
    }

    @GetMapping("/similar")
    @ApiOperation(value = "Get top 5 most similar computers.")
    public ResponseEntity<CbrOutput> getSimilar(@RequestParam String name) {
        return ResponseEntity.ok(new CbrOutput(this.service.getSimilar(name)));
    }

    @GetMapping("/similar/v2")
    @ApiOperation(value = "Get top 5 most similar computers.")
    public ResponseEntity<CbrOutput> getSimilar(ComputerDescription computerDescription) {
        return ResponseEntity.ok(new CbrOutput(this.service.getSimilar(computerDescription)));
    }

}
