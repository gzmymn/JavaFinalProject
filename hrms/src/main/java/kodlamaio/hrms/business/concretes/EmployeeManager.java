package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.business.validationRules.abstracts.EmployeeValidatorService;
import kodlamaio.hrms.core.utilities.business.BusinessEngine;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.verifyTool.CodeGenerator;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Employee;


@Service
public class EmployeeManager extends UserManager<Employee> implements EmployeeService {

	private final  EmployeeValidatorService employeeValidatorService;
		
	@Autowired
	public EmployeeManager(UserDao<Employee> userDao, EmployeeValidatorService employeeValidatorService) {
		super(userDao);
		this.employeeValidatorService=employeeValidatorService;				
	}
	
	@Override
	public Result add(Employee employee) {
		Result result=BusinessEngine.run(employeeValidatorService.isEmployeeNullCheck(employee));
		if (!result.isSuccess()) {
			return result;
		}
		
		employee.setUid(CodeGenerator.generateUuidCode());
		super.add(employee);
		return new SuccessResult("Sistem çalışanı(Employee) eklendi.");
	}
	
}
