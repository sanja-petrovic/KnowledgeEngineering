package ftn.knowledge.engineering.ComputerRecommender.controller;

import ftn.knowledge.engineering.ComputerRecommender.dto.FuzzyInput;
import ftn.knowledge.engineering.ComputerRecommender.dto.FuzzyOutput;
import ftn.knowledge.engineering.ComputerRecommender.service.fuzzy.FuzzyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/fuzzy", tags = "Fuzzy systems")
@RequestMapping(value = "/fuzzy")
public class FuzzyController {
    private final FuzzyService service;

    public FuzzyController(FuzzyService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Evaluate how suitable a computer is for different purposes based on its components.", httpMethod = "POST")
    public ResponseEntity<FuzzyOutput> evaluate(@RequestBody FuzzyInput input) {
        return ResponseEntity.ok(service.evaluate(input));
    }
}
