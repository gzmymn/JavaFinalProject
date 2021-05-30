package kodlamaio.hrms.business.validationRules.concretes;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

import kodlamaio.hrms.business.validationRules.abstracts.EmployeeValidatorService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.Employee;

@Component
public class EmployeeValidator extends UserValidator implements EmployeeValidatorService {

	@Override
	public Result isEmployeeNullCheck(Employee employee) {
		String firstName=employee.getFirstName();
		String lastName=employee.getLastName();
		if (!super.userNullCheck(employee).isSuccess() || Strings.isNullOrEmpty(firstName) || Strings.isNullOrEmpty(lastName)) {
			return new ErrorResult("İsim ve soyisim alanları boş bırakılamaz!");
		}
		return new SuccessResult();
	}

}
