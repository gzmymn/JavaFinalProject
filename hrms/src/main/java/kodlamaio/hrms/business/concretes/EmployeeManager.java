package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Employee;


@Service
public class EmployeeManager extends UserManager<Employee> implements EmployeeService {

	private EmployeeDao employeeDao;
		
		@Autowired
		public EmployeeManager(UserDao<Employee> userDao) {
			super(userDao);
			this.employeeDao = (EmployeeDao) userDao;			
		}
}
