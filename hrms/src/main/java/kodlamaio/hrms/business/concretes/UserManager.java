package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.business.BusinessEngine;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.verifyTool.CodeGenerator;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.abstracts.User;

@Service
public class UserManager<T extends User> implements UserService<T> {

	private UserDao<T> userDao;
	
	@Autowired
	public UserManager(UserDao<T> userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<T>> getAll() {
		
		return new SuccessDataResult<List<T>>(this.userDao.findAll(), "User Listed.");
	}

	@Override
	public Result add(T t) {
		Result result = BusinessEngine.run(isEmailExist(t.getEmail())) ;
		if (!result.isSuccess()) {
			return result;
		}
		t.setUid(CodeGenerator.generateUuidCode());
		this.userDao.save(t);
		return new SuccessResult("User Added.");
	}
	
	public Result isEmailExist(String email) {
		if (userDao.findByEmail(email).isPresent()) {
			return new ErrorResult("Email adresi zaten mevcut!");
		}
		return new SuccessResult();
	}

}
