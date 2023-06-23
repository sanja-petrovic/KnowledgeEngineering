package ftn.knowledge.engineering.ComputerRecommender.controller;

import ftn.knowledge.engineering.ComputerRecommender.dto.CbrOutput;
import ftn.knowledge.engineering.ComputerRecommender.service.cbr.CbrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<CbrOutput> getDescriptions() {
        return ResponseEntity.ok(new CbrOutput(this.service.getDescriptions()));
    }
}
