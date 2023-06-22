package ftn.knowledge.engineering.ComputerRecommender.service.cbr;

import ftn.knowledge.engineering.ComputerRecommender.repository.cbr.CbrRepository;
import org.springframework.stereotype.Service;

@Service
public class CbrServiceImpl implements CbrService {
    private final CbrRepository repository;


    public CbrServiceImpl(CbrRepository repository) {
        this.repository = repository;
    }
}
