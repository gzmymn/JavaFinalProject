package kodlamaio.hrms.business.abstracts.auth;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.dtos.RegisterForEmployerDto;

public interface EmployerAuthService {

	Result register(RegisterForEmployerDto registerForEmployerDto);
	
}
