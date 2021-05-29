package kodlamaio.hrms.business.abstracts.auth;

import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.dtos.RegisterForEmployerDto;

public interface EmployerAuthService extends UserAuthService<RegisterForEmployerDto, Employer> {

	
	
}
