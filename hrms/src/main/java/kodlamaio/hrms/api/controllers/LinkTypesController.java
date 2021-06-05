package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.LinkTypeService;
import kodlamaio.hrms.entities.concretes.LinkType;

@RestController
@RequestMapping("/api/linkTypes")
public class LinkTypesController {

    private LinkTypeService linkTypeService;
    @Autowired
    public LinkTypesController(LinkTypeService linkTypeService) {
        this.linkTypeService = linkTypeService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(linkTypeService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody LinkType linkType){
        return ResponseEntity.ok(linkTypeService.add(linkType));
    }

}
