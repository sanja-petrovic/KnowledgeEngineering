package ftn.knowledge.engineering.ComputerRecommender.controller;

import ftn.knowledge.engineering.ComputerRecommender.dto.BayesInput;
import ftn.knowledge.engineering.ComputerRecommender.dto.BayesOutput;
import ftn.knowledge.engineering.ComputerRecommender.service.bayes.BayesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "/bayes", tags = "Bayes network")
@RequestMapping(value = "/bayes")

public class BayesController {
    private final BayesService service;

    public BayesController(BayesService service) {
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Evaluate cause of symptoms.", httpMethod = "POST")
    public ResponseEntity<List<BayesOutput>> evaluateCause(@RequestBody BayesInput input) {
        return ResponseEntity.ok(service.evaluateCause(input));
    }
}
