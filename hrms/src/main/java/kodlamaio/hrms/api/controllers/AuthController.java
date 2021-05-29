package kodlamaio.hrms.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.auth.CandidateAuthService;
import kodlamaio.hrms.business.abstracts.auth.EmployerAuthService;
import kodlamaio.hrms.business.abstracts.auth.VerifyService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.dtos.RegisterForCandidateDto;
import kodlamaio.hrms.entities.dtos.RegisterForEmployerDto;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final CandidateAuthService candidateAuthService;
	private final EmployerAuthService employerAuthService;
	private final VerifyService verifyService;
	
	@Autowired
	public AuthController(CandidateAuthService candidateAuthService, EmployerAuthService employerAuthService,
			VerifyService verifyService) {	
		this.candidateAuthService = candidateAuthService;
		this.employerAuthService = employerAuthService;
		this.verifyService = verifyService;
	}
	
	@PostMapping("/employer/register")
	public Result registerForEmployer(@RequestBody @Valid RegisterForEmployerDto registerForEmployerDto){
		
		return employerAuthService.register(registerForEmployerDto);
	}
	
	@PostMapping("/candidate/register")
	public Result registerForCandidate(@RequestBody @Valid RegisterForCandidateDto registerForCandidateDto){
		
		return candidateAuthService.register(registerForCandidateDto);
	}
	
	@GetMapping("/verify")
	public Result verify(@RequestParam("uid") String uid, @RequestParam("activationCode") String activaionCode) {
		
		return verifyService.verify(uid, activaionCode);
	}
	
	@GetMapping("/resend")
	public Result reSendEmail(@RequestParam("uid") String uid) {
		
		return employerAuthService.reSendEmail(uid);
	}
}
