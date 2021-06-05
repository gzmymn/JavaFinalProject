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

import kodlamaio.hrms.business.abstracts.LanguageCandidateService;
import kodlamaio.hrms.entities.concretes.LanguageCandidate;

@RestController
@RequestMapping("/api/languageCandidates")
public class LanguageCandidatesController {

    private LanguageCandidateService languageCandidateService;
    
    @Autowired
    public LanguageCandidatesController(LanguageCandidateService languageCandidateService) {
        this.languageCandidateService = languageCandidateService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(languageCandidateService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody LanguageCandidate languageCandiate){
        return ResponseEntity.ok(languageCandidateService.add(languageCandiate));
    }

    @GetMapping("/getByCandidateId")
    public ResponseEntity<?> getByCandidateId(@RequestParam int candidateId){
        return ResponseEntity.ok(languageCandidateService.getByCandidateId(candidateId));
    }

}
