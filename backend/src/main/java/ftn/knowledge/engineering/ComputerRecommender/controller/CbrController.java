package ftn.knowledge.engineering.ComputerRecommender.controller;

import ftn.knowledge.engineering.ComputerRecommender.service.cbr.CbrService;
import io.swagger.annotations.Api;
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
}
