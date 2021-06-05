package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.WorkplaceCandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.WorkplaceCandidateDao;
import kodlamaio.hrms.entities.concretes.WorkplaceCandidate;

@Service
public class WorkplaceCandidateManager implements WorkplaceCandidateService {

	private WorkplaceCandidateDao workplaceCandidateDao;
	
	@Autowired
	public WorkplaceCandidateManager(WorkplaceCandidateDao workplaceCandidateDao) {
		
		this.workplaceCandidateDao = workplaceCandidateDao;
	}

	@Override
	public DataResult<List<WorkplaceCandidate>> getAll() {
		
		return new SuccessDataResult<List<WorkplaceCandidate>>(workplaceCandidateDao.findAll());
	}

	@Override
	public DataResult<List<WorkplaceCandidate>> getByCandidateId(int candidateId) {
		
		return new SuccessDataResult<List<WorkplaceCandidate>>(workplaceCandidateDao.getByCandidate_Id(candidateId));
	}

	@Override
	public DataResult<List<WorkplaceCandidate>> getByCandidateIdOrderByDateOfLeaveDesc(int candidateId) {
		
		return new SuccessDataResult<List<WorkplaceCandidate>>(workplaceCandidateDao.getByCandidate_IdOrderByDateOfQuitDesc(candidateId));
	}

	@Override
	public Result add(WorkplaceCandidate workplaceCandidate) {
		workplaceCandidateDao.save(workplaceCandidate);
		return new SuccessResult("İş arayan için iş yeri eklendi.");
	}

}
