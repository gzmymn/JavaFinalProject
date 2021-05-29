package kodlamaio.hrms.business.concretes.auth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.UserService;
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
import kodlamaio.hrms.entities.abstracts.User;
import kodlamaio.hrms.entities.concretes.ActivationCode;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.dtos.RegisterForCandidateDto;

@Service
public class CandidateAuthManager extends UserAuthManager<RegisterForCandidateDto, Candidate> implements CandidateAuthService{
	
	public CandidateAuthManager(AuthValidatorService authValidatorService, UserService<Candidate> userService, ActivationCodeService activationCodeService, EmailSenderService emailSenderService) {
		super(authValidatorService, userService, activationCodeService, emailSenderService);
	}
	
	@Override
	public Candidate newUserInstance(RegisterForCandidateDto registerForCandidateDto) {
		
		return new Candidate(registerForCandidateDto.getFirstName(), registerForCandidateDto.getLastName(), registerForCandidateDto.getNationalIdentity(),
				registerForCandidateDto.getDateOfBirth(), registerForCandidateDto.getEmail(), registerForCandidateDto.getPassword());
	}

	
	
	


}
