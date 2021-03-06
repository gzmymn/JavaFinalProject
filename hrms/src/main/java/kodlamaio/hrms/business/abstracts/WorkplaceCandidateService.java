package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.WorkplaceCandidate;


public interface WorkplaceCandidateService {

	DataResult<List<WorkplaceCandidate>> getAll();
	DataResult<List<WorkplaceCandidate>> getByCandidateId(int candidateId);
	DataResult<List<WorkplaceCandidate>> getByCandidateIdOrderByDateOfLeaveDesc(int candidateId);
	Result add(WorkplaceCandidate workplaceCandidate);
}
