package kodlamaio.hrms.business.validationRules.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface AuthValidatorService {

	Result isPasswordConfirmed(String password, String confirmPassword);
}
