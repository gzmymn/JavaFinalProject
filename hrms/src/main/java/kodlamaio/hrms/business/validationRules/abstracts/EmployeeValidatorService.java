package kodlamaio.hrms.business.validationRules.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employee;

public interface EmployeeValidatorService {

	public Result isEmployeeNullCheck(Employee employee);
}
