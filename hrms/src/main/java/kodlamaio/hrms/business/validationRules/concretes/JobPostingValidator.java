package kodlamaio.hrms.business.validationRules.concretes;

import org.springframework.stereotype.Component;
import com.google.common.base.Strings;
import kodlamaio.hrms.business.validationRules.abstracts.JobPostingValidatorService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.JobPosting;

@Component
public class JobPostingValidator implements JobPostingValidatorService {

	@Override
	public Result isNullCheck(JobPosting jobPosting) {
		
		String definition=jobPosting.getJobDefinition();
		if (Strings.isNullOrEmpty(definition)) {
			return new ErrorResult("İş tanımı boş bırakılamaz!");
		}
		return new SuccessResult();
	}

}
