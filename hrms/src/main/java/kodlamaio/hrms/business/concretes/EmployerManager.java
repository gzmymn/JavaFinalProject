package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.validationRules.abstracts.EmployerValidatorService;
import kodlamaio.hrms.core.utilities.business.BusinessEngine;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager extends UserManager<Employer> implements EmployerService {

	private EmployerValidatorService employerValidatorService;
	
	@Autowired
    public EmployerManager(UserDao<Employer> userDao, EmployerValidatorService employerValidatorService){
        super(userDao);
        this.employerValidatorService = employerValidatorService;
    }
	
	 @Override
	 public Result add(Employer employer) {
		 
		 Result result = BusinessEngine.run(employerValidatorService.employerNullCheck(employer),
				 employerValidatorService.isEmailDomainCheck(employer));
		 
		 if (result.isSuccess()) {
			return super.add(employer);
		}
		 
		 return result;
	 }
	

}
