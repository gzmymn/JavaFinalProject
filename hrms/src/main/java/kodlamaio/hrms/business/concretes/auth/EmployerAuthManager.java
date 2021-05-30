package kodlamaio.hrms.business.concretes.auth;

import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.abstracts.auth.EmployerAuthService;
import kodlamaio.hrms.business.validationRules.abstracts.AuthValidatorService;
import kodlamaio.hrms.core.utilities.email.EmailSenderService;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.RegisterForEmployerDto;

@Service
public class EmployerAuthManager extends UserAuthManager<RegisterForEmployerDto, Employer> implements EmployerAuthService {
	
	public EmployerAuthManager(AuthValidatorService authValidatorService, UserService<Employer> userService, ActivationCodeService activationCodeService, EmailSenderService emailSenderService) {
        super(authValidatorService, userService, activationCodeService, emailSenderService);
    }
	@Override
	public Employer newUserInstance(RegisterForEmployerDto registerForEmployerDto) {

		return new Employer(registerForEmployerDto.getCompanyName(), registerForEmployerDto.getWebAddress(), registerForEmployerDto.getPhoneNumber(),
				registerForEmployerDto.getEmail(), registerForEmployerDto.getPassword());
	}

	


}
