package kodlamaio.hrms.business.validationRules.concretes;

import org.springframework.stereotype.Component;

import kodlamaio.hrms.business.validationRules.abstracts.EmployerValidatorService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.Employer;

@Component
public class EmployerValidator extends UserValidator implements EmployerValidatorService {

	@Override
	public Result employerNullCheck(Employer employer) {
		
		String compnayName=employer.getCompanyName();
		String webAddress=employer.getWebAddress();
		String phoneNumber=employer.getPhoneNumber();
		
		if (!super.userNullCheck(employer).isSuccess()&&(compnayName==null || compnayName.isBlank()) 
				&& (webAddress==null || webAddress.isBlank())
				&& (phoneNumber==null || phoneNumber.isBlank())){
			return new ErrorResult("Alanlar boş olamaz!");
		}
		return new SuccessResult();
	}

	@Override
	public Result isEmailDomainCheck(Employer employer) {
		
		String email=employer.getEmail();
		String webAddress=employer.getWebAddress();
		String domain=webAddress.split("www.")[1];
		
		if (domain.equals(email.split("@")[1])) {
			return new SuccessResult();
		}
		
		return new ErrorResult();
	}
	
	/*private Result emailExist(String email) {
		if(employerDao.findAllByEmail(email).stream().count()!= 0) {
			return new ErrorResult("bu e mail mevcut");
		}
		return new SuccessResult();
	}
	*/
	
	/*private Result isRealEmployer(Employer employer) {
		
		 String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(employer.getEmail());
		
		if(!employer.getEmail().contains(employer.getWebAddress())) {
			return new ErrorResult("domain aynı değil");
		}
		else if(matcher.matches()) {
			return new ErrorResult("");
		}
		return new SuccessResult();		
	}
	*/
}
