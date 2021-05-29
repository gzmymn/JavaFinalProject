package kodlamaio.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.verifyTool.CodeGenerator;
import kodlamaio.hrms.dataAccess.abstracts.ActivationCodeDao;
import kodlamaio.hrms.entities.concretes.ActivationCode;

@Service
public class ActivationCodeManager implements ActivationCodeService{

	private ActivationCodeDao activationCodeDao;
	
	@Autowired
	public ActivationCodeManager(ActivationCodeDao activationCodeDao) {		
		this.activationCodeDao = activationCodeDao;
	}

	@Override
	public DataResult<List<ActivationCode>> getAll() {
		
		return new SuccessDataResult<List<ActivationCode>>(this.activationCodeDao.findAll(), "Aktivasyon kodları listelendi.");
	}

	@Override
	public Result add(ActivationCode activationCode) {
		
		activationCode.setExprationDate(LocalDateTime.now().plusMinutes(10));
		activationCode.setUid(CodeGenerator.generateUuidCode());
		activationCodeDao.save(activationCode);
		
		return new SuccessResult("Aktivasyon kodu eklendi.");
	}

	@Override
	public Result update(ActivationCode activationCode) {
		
		activationCodeDao.save(activationCode);
        return new SuccessResult("Aktivasyon kodu güncellendi.");
	}

	@Override
	public DataResult<Optional<ActivationCode>> getByUserUid(String uid) {
		
		return new SuccessDataResult<Optional<ActivationCode>>(activationCodeDao.findByUserUid(uid));
	}

}
