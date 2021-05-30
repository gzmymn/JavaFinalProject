package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;

public interface JobPostingService {

	DataResult<List<JobPosting>> getAll();	
	Result add(JobPosting jobPosting);	
	Result changeActive(int postingId);
	DataResult<List<JobPosting>> getAllByActivated();
	DataResult<List<JobPosting>> getAllByActivatedAndByDay(int day);
	DataResult<List<JobPosting>> getAllByActivatedAndByEmployer(int employerId);
}
