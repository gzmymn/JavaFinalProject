package kodlamaio.hrms.business.concretes.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.auth.EmployerAuthService;
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
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.RegisterForEmployerDto;

@Service
public class EmployerAuthManager implements EmployerAuthService {

	private AuthValidatorService authValidatorService;
	private EmployerService employerService;
	private ActivationCodeService activationCodeService;
	private EmailSenderService emailSenderService;
	
	
	@Autowired
	public EmployerAuthManager(AuthValidatorService authValidatorService, EmployerService employerService,
			ActivationCodeService activationCodeService, EmailSenderService emailSenderService) {
		super();
		this.authValidatorService = authValidatorService;
		this.employerService = employerService;
		this.activationCodeService = activationCodeService;
		this.emailSenderService = emailSenderService;
	}



	@Override
	public Result register(RegisterForEmployerDto registerForEmployerDto) {

		Result result = BusinessEngine.run(authValidatorService.isPasswordConfirmed(registerForEmployerDto.getPassword(), registerForEmployerDto.getConfirmPassword()));
		if (!result.isSuccess()) {
			return result;
		}
		
		DataResult<Employer> addResult=employerAdd(registerForEmployerDto);
		if (!addResult.isSuccess()) {
			return addResult;
		}
		
		String code=CodeGenerator.generateUuidCode();
		Result codeAddResult = activationCodeAdd(addResult.getData().getId(), code);
		if (!codeAddResult.isSuccess()) {
			return codeAddResult;			
		}
		
		emailSenderService.send("Doğrulama için linke tıklayınız : https://dogrulama.deneme/" + code);
		
		return new SuccessResult("Kullanıcı eklendi.");
	}
	
	
	private DataResult<Employer> employerAdd(RegisterForEmployerDto registerForEmployerDto){
		
		Employer employer = new Employer (registerForEmployerDto.getCompanyName(), registerForEmployerDto.getWebAddress(), registerForEmployerDto.getPhoneNumber(),
				registerForEmployerDto.getEmail(), registerForEmployerDto.getPassword());
		
		Result addResult=employerService.add(employer);
		if (!addResult.isSuccess()) {
			return new ErrorDataResult<Employer>(null, addResult.getMessage());
		}
		
		return new SuccessDataResult<Employer>(employer);
	}
	
	private Result activationCodeAdd(int userId, String code) {
		
		ActivationCode activationCode = new ActivationCode(userId, code);
		Result activationResult = activationCodeService.add(activationCode);
		if (!activationResult.isSuccess()) {
			return activationResult;
		}
		
		return new SuccessResult();
		
	}

}
