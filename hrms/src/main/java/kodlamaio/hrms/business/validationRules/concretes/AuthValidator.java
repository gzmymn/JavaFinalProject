package kodlamaio.hrms.business.validationRules.concretes;

import org.springframework.stereotype.Component;

import kodlamaio.hrms.business.validationRules.abstracts.AuthValidatorService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;

@Component
public class AuthValidator implements AuthValidatorService {

	@Override
	public Result isPasswordConfirmed(String password, String confirmPassword) {
		
		if (password.equals(confirmPassword)) {
			return new SuccessResult();
		}
		return new ErrorResult("Parola eşleşmedi!");
	}

}
