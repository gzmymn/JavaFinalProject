package kodlamaio.hrms.business.abstracts.auth;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.abstracts.Dto;
import kodlamaio.hrms.entities.abstracts.User;

public interface UserAuthService <TDto extends Dto, TUser extends User> {

	Result register(TDto tDto);
	Result reSendEmail(String uid);
}
