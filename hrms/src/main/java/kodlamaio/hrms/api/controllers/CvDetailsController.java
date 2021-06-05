package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CvDetailService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.CvDetail;

@RestController
@RequestMapping("/api/cvdetails")
public class CvDetailsController {

	private CvDetailService cvDetailService;

	@Autowired
	public CvDetailsController(CvDetailService cvDetailService) {
		super();
		this.cvDetailService = cvDetailService;
	}
	
	@GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(cvDetailService.getAll());
    }

    @GetMapping("/getByCandidateId")
    public ResponseEntity<?> getByCandidateId(int candidateId){
        return ResponseEntity.ok(cvDetailService.getByCandidateId(candidateId));
    }
	
	@PostMapping("/add")
	public Result add(@RequestBody CvDetail cvDetail) {
		return cvDetailService.add(cvDetail);
	}
	
	@PostMapping("/uploadImage")
	public ResponseEntity<?> uploadImage(@RequestParam ("file") MultipartFile multiPartFile, @RequestParam int candidateId){
		return ResponseEntity.ok(cvDetailService.uploadPhoto(multiPartFile, candidateId));
	}
}
