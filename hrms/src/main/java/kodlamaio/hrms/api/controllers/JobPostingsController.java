package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;

@RestController
@RequestMapping("/api/jobpostings")
public class JobPostingsController {

	private final JobPostingService jobPostingService;

	@Autowired
	public JobPostingsController(JobPostingService jobPostingService) {
		super();
		this.jobPostingService = jobPostingService;
	}
	
	@GetMapping("/toggleactive")
	public ResponseEntity<?> toggleActive(@RequestParam int postingId){
		Result result = this.jobPostingService.changeActive(postingId);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody JobPosting jobPosting){
		Result result=this.jobPostingService.add(jobPosting);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getbyemployer")
	public ResponseEntity<?> getAllByActivatedAndByEmployer(@RequestParam int employerId){
		Result result = this.jobPostingService.getAllByActivatedAndByEmployer(employerId);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getbyday")
	public ResponseEntity<?> getAllByActivatedAndByDay(@RequestParam int day){
		Result result=this.jobPostingService.getAllByActivatedAndByDay(day);
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getactives")
	public ResponseEntity<?> getAllByActivatedAndyDay(){
		Result result = this.jobPostingService.getAllByActivated();
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result); 
	}
	
	@GetMapping("/getjobpostingdetails")
	public ResponseEntity<?> getJobPostingDetails(){
		Result result = this.jobPostingService.getJobPostingDetails();
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		return ResponseEntity.ok(result);
	}
	
}
