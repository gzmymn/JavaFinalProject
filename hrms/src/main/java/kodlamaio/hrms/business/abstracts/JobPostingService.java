package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.dtos.JobPostingDetailsDto;

public interface JobPostingService {

	DataResult<List<JobPosting>> getAll();	
	Result add(JobPosting jobPosting);	
	Result changeActive(int postingId);
	DataResult<List<JobPosting>> getAllByActivated();
	DataResult<List<JobPosting>> getAllByActivatedAndByDay(int day);
	DataResult<List<JobPosting>> getAllByActivatedAndByEmployer(int employerId);
	DataResult<List<JobPostingDetailsDto>> getJobPostingDetails();
}
