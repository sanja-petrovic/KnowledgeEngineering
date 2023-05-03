package ftn.knowledge.engineering.ComputerRecommender.controller;

import ftn.knowledge.engineering.ComputerRecommender.service.OntologyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
