package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobPositionService;
import kodlamaio.hrms.core.utilities.business.BusinessEngine;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.verifyTool.CodeGenerator;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService {

	private JobPositionDao jobPositionDao;
	
	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		super();
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		
		return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(), "İş pozisyonları listelendi.");
	}	
	

	@Override
	public Result add(JobPosition jobPosition) {
		
		Result result = BusinessEngine.run(isPositionNullCheck(jobPosition), isPositionRepeatControl(jobPosition));
		
		if (result.isSuccess()) {
			jobPosition.setUid(CodeGenerator.generateUuidCode());
			jobPositionDao.save(jobPosition);
			return new SuccessResult("Job Position eklendi.");
		}
		return result;
		
	}
	
	
	private Result isPositionNullCheck(JobPosition jobPosition) {
		if (jobPosition.getPosition()==null || jobPosition.getPosition().isBlank()) {
			return new ErrorResult("Pozisyonlar boş bırakılamaz.");
		}
		return new SuccessResult();
	}
	
	private Result isPositionRepeatControl(JobPosition jobPosition) {
		
		if(jobPositionDao.findByPosition(jobPosition.getPosition()).isPresent()){
            return new ErrorResult("Girilen pozisyon adı zaten mevcut, yeni bir pozisyon adı giriniz.");
        }
        return new SuccessResult();
	}
	
}	
	


