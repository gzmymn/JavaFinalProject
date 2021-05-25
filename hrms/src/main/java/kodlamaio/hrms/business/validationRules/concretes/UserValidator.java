package kodlamaio.hrms.business.validationRules.concretes;

import kodlamaio.hrms.business.validationRules.abstracts.UserValidatorService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.abstracts.User;

public class UserValidator implements UserValidatorService {

	@Override
	public Result userNullCheck(User user) {
		
		if ((user.getEmail()==null || user.getEmail().isBlank() && (user.getPassword()==null || user.getPassword().isBlank()))) {
			return new ErrorResult("Email ve parola boş olamaz!");
		}
		return new SuccessResult();
	}

}
