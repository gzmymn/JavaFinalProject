package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.SchoolDepartmentService;
import kodlamaio.hrms.entities.concretes.SchoolDepartment;

@RestController
@RequestMapping("/api/schoolDepartment")
public class SchoolDepartmentsController {

    private SchoolDepartmentService schoolDepartmentService;

    @Autowired
    public SchoolDepartmentsController(SchoolDepartmentService schoolDepartmentService) {
        this.schoolDepartmentService = schoolDepartmentService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.schoolDepartmentService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody SchoolDepartment schoolDepartment){
        return ResponseEntity.ok(this.schoolDepartmentService.add(schoolDepartment));
    }

}
