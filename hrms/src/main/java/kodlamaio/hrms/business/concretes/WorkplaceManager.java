package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.WorkplaceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.WorkplaceDao;
import kodlamaio.hrms.entities.concretes.Workplace;

@Service
public class WorkplaceManager implements WorkplaceService {

	private WorkplaceDao workplaceDao;
		
	@Autowired
	public WorkplaceManager(WorkplaceDao workplaceDao) {
		
		this.workplaceDao = workplaceDao;
	}

	@Override
	public DataResult<List<Workplace>> getAll() {
		
		return new SuccessDataResult<List<Workplace>>(workplaceDao.findAll());
	}

	@Override
	public Result add(Workplace workplace) {
		workplaceDao.save(workplace);
		return new SuccessResult("İş yeri eklendi.");
	}

}
