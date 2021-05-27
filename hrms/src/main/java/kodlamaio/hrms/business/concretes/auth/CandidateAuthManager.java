package kodlamaio.hrms.business.concretes.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.auth.CandidateAuthService;
import kodlamaio.hrms.business.validationRules.abstracts.AuthValidatorService;
import kodlamaio.hrms.core.utilities.business.BusinessEngine;
import kodlamaio.hrms.core.utilities.email.EmailSenderService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.verifyTool.CodeGenerator;
import kodlamaio.hrms.entities.concretes.ActivationCode;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.dtos.RegisterForCandidateDto;

@Service
public class CandidateAuthManager implements CandidateAuthService{

	private AuthValidatorService authValidatorService;
    private CandidateService candidateService;
    private ActivationCodeService activationCodeService;
    private EmailSenderService emailSenderService;
	
    @Autowired
	public CandidateAuthManager(AuthValidatorService authValidatorService, CandidateService candidateService,
			ActivationCodeService activationCodeService, EmailSenderService emailSenderService) {
		
		this.authValidatorService = authValidatorService;
		this.candidateService = candidateService;
		this.activationCodeService = activationCodeService;
		this.emailSenderService = emailSenderService;
	}

	@Override
	public Result register(RegisterForCandidateDto registerForCandidateDto) {

		Result result=BusinessEngine.run(authValidatorService.isPasswordConfirmed(registerForCandidateDto.getPassword(), registerForCandidateDto.getConfirmPassword()));
		if (!result.isSuccess()) {
			return result;
		}
		
		DataResult<Candidate> addResult= candidateAdd(registerForCandidateDto);
		if (!addResult.isSuccess()) {
			return addResult;
		}
		
		String code=CodeGenerator.generateUuidCode();
		Result codeAddResult=activationCodeAdd(addResult.getData().getId(), code);
		if (!codeAddResult.isSuccess()) {
			return codeAddResult;
		}
		emailSenderService.send("Doğrulama için linke tıklayınız : https://localhost:8080/api/auth/verify?activationCode="+code+"&userId="+addResult.getData().getId());
		
		return new SuccessResult("Kullanıcı eklendi.");
	}
	
	private DataResult<Candidate> candidateAdd(RegisterForCandidateDto registerForCandidateDto){
		
		Candidate candidate = new Candidate(registerForCandidateDto.getFirstName(), registerForCandidateDto.getLastName(), registerForCandidateDto.getNationalIdentity(), 
				registerForCandidateDto.getDateOfBirth(), registerForCandidateDto.getEmail(), registerForCandidateDto.getPassword());
		Result addResult=candidateService.add(candidate);
		if (!addResult.isSuccess()) {
			return new ErrorDataResult<Candidate>(null, addResult.getMessage());
		}
		
		return new SuccessDataResult<Candidate>(candidate);
	}
	
	private Result activationCodeAdd(int userId, String code) {
		
		ActivationCode activationCode=new ActivationCode(userId, code);
		Result activationResult = activationCodeService.add(activationCode);
		if (!activationResult.isSuccess()) {
			return activationResult;
		}
		
		return new SuccessResult();
	}

}
