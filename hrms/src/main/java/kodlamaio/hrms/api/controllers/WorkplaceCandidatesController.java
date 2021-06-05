package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.WorkplaceCandidateService;
import kodlamaio.hrms.entities.concretes.WorkplaceCandidate;

@RestController
@RequestMapping("/api/workplaceCandidates")
public class WorkplaceCandidatesController {

    private WorkplaceCandidateService workplaceCandidateService;
    
    @Autowired
    public WorkplaceCandidatesController(WorkplaceCandidateService workplaceCandidateService) {
        this.workplaceCandidateService = workplaceCandidateService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(workplaceCandidateService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody WorkplaceCandidate workplaceCandidate){
        return ResponseEntity.ok(workplaceCandidateService.add(workplaceCandidate));
    }

    @GetMapping("/getByCandidateId")
    public ResponseEntity<?> getByCandidateId(@RequestParam int candidateId){
        return ResponseEntity.ok(workplaceCandidateService.getByCandidateId(candidateId));
    }

    @GetMapping("/getByCandidateIdOrderByDateOfQuitDesc")
    public ResponseEntity<?> getByCandidateIdOrderByDateOfQuitDesc(int candidateId){
        return ResponseEntity.ok(workplaceCandidateService.getByCandidateIdOrderByDateOfLeaveDesc(candidateId));
    }

}
