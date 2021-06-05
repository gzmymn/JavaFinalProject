package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.LanguageLevelService;
import kodlamaio.hrms.entities.concretes.LanguageLevel;

@RestController
@RequestMapping("/api/languageLevels")
public class LanguageLevelsController {

    private LanguageLevelService languageLevelService;

    @Autowired
    public LanguageLevelsController(LanguageLevelService languageLevelService) {
        this.languageLevelService = languageLevelService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(languageLevelService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody LanguageLevel languageLevel) {
        return ResponseEntity.ok(languageLevelService.add(languageLevel));
    }

}
