package kodlamaio.hrms.business.validationRules.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobPosting;

public interface JobPostingValidatorService {

	public Result isNullCheck(JobPosting jobPosting);
}
