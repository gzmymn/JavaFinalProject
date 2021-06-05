package kodlamaio.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.JobPostingService;
import kodlamaio.hrms.business.validationRules.abstracts.JobPostingValidatorService;
import kodlamaio.hrms.core.utilities.business.BusinessEngine;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.verifyTool.CodeGenerator;
import kodlamaio.hrms.dataAccess.abstracts.JobPostingDao;
import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.dtos.JobPostingDetailsDto;

@Service
public class JobPostingManager implements JobPostingService {

	private final JobPostingDao jobPostingDao;
	private final JobPostingValidatorService jobPostingValidatorService;
	
	@Autowired
	public JobPostingManager(JobPostingDao jobPostingDao, JobPostingValidatorService jobPostingValidatorService) {
		super();
		this.jobPostingDao = jobPostingDao;
		this.jobPostingValidatorService=jobPostingValidatorService;
	}

	@Override
	public DataResult<List<JobPosting>> getAll() {
		
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.findAll(),"İş ilanları listelendi");
	}

	@Override
	public Result add(JobPosting jobPosting) {
		
		Result result=BusinessEngine.run(jobPostingValidatorService.isNullCheck(jobPosting));
		if (!result.isSuccess()) {
			return result;
		}
		jobPosting.setUid(CodeGenerator.generateUuidCode());
		jobPostingDao.save(jobPosting);
		return new SuccessResult("İş ilanı eklendi.");
	}

	@Override
	public Result changeActive(int postingId) {
		JobPosting jobPosting=this.jobPostingDao.getById(postingId);
		Result result=BusinessEngine.run(checkJobPostingIsExists(jobPosting));
		if (!result.isSuccess()) {
			return result;
		}
		JobPosting updatePosting=jobPosting;
		updatePosting.setActivated(!updatePosting.isActivated());
		this.jobPostingDao.save(updatePosting);
		return new SuccessResult("İş ilanı güncellendi.");
	}

	@Override
	public DataResult<List<JobPosting>> getAllByActivated() {
		
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.getByIsActivatedTrueOrderByCreatedDateDesc());
	}

	@Override
	public DataResult<List<JobPosting>> getAllByActivatedAndByDay(int day) {
		LocalDateTime finisDate=LocalDateTime.now().minusDays(day);		
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.getByCreatedDateBetweenAndIsActivatedTrueOrderByCreatedDateDesc(finisDate, LocalDateTime.now()));
	}

	@Override
	public DataResult<List<JobPosting>> getAllByActivatedAndByEmployer(int employerId) {
		
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.getByEmployer_IdAndIsActivatedTrue(employerId));
	}
	
	private Result checkJobPostingIsExists(JobPosting jobPosting) {
		if (jobPosting==null) {
			return new ErrorResult("İş ilanı mevcut değildir!");
		}
		return new SuccessResult();
	}

	@Override
	public DataResult<List<JobPostingDetailsDto>> getJobPostingDetails() {
		
		return new SuccessDataResult<List<JobPostingDetailsDto>>(jobPostingDao.getJobPostingDetails());
	}

}
