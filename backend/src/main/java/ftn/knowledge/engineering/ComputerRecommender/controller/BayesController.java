package ftn.knowledge.engineering.ComputerRecommender.controller;

import ftn.knowledge.engineering.ComputerRecommender.dto.FuzzyInput;
import ftn.knowledge.engineering.ComputerRecommender.dto.FuzzyOutput;
import ftn.knowledge.engineering.ComputerRecommender.service.BayesService;
import ftn.knowledge.engineering.ComputerRecommender.service.FuzzyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unbbayes.prs.exception.InvalidParentException;

import java.io.FileNotFoundException;

@RestController
@Api(value = "/bayes", tags = "bayes reasoning")
@RequestMapping(value = "/bayes")
public class BayesController {
    private final BayesService service;
    public BayesController(BayesService service){this.service = service;}
  
}
