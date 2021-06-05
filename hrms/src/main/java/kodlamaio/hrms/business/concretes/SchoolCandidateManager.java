package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SchoolCandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SchoolCandidateDao;
import kodlamaio.hrms.entities.concretes.SchoolCandidate;

@Service
public class SchoolCandidateManager implements SchoolCandidateService {

	private SchoolCandidateDao schoolCandidateDao;
	
	@Autowired
	public SchoolCandidateManager(SchoolCandidateDao schoolCandidateDao) {
		
		this.schoolCandidateDao = schoolCandidateDao;
	}

	@Override
	public DataResult<List<SchoolCandidate>> getAll() {
		
		return new SuccessDataResult<List<SchoolCandidate>>(schoolCandidateDao.findAll());
	}

	@Override
	public DataResult<List<SchoolCandidate>> getByCandidateId(int candidateId) {
		
		return new SuccessDataResult<List<SchoolCandidate>>(schoolCandidateDao.getByCandidate_Id(candidateId));
	}

	@Override
	public DataResult<List<SchoolCandidate>> getByCandidateIdOrderByDateOfGraduationDesc(int candidateId) {
		
		return new SuccessDataResult<List<SchoolCandidate>>(schoolCandidateDao.getByCandidate_IdOrderByDateOfGraduationDesc(candidateId));
	}

	@Override
	public Result add(SchoolCandidate schoolCandidate) {
		schoolCandidateDao.save(schoolCandidate);
		return new SuccessResult("İş arayan için okul eklendi.") ;
	}

}
