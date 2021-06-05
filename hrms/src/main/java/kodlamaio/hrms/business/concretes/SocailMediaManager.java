package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SocialMediaService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SocialMediaDao;
import kodlamaio.hrms.entities.concretes.SocialMedia;

@Service
public class SocailMediaManager implements SocialMediaService {

	private SocialMediaDao socialMediaDao;
	
	@Autowired
	public SocailMediaManager(SocialMediaDao socialMediaDao) {
		
		this.socialMediaDao = socialMediaDao;
	}

	@Override
	public DataResult<List<SocialMedia>> getAll() {
		
		return new SuccessDataResult<List<SocialMedia>>(socialMediaDao.findAll());
	}

	@Override
	public DataResult<List<SocialMedia>> getByCandidateId(int candidateId) {
		
		return new SuccessDataResult<List<SocialMedia>>(socialMediaDao.getByCandidate_Id(candidateId));
	}

	@Override
	public Result add(SocialMedia socialMedia) {
		socialMediaDao.save(socialMedia);
		return new SuccessResult("Sosyal medya bilgisi eklendi.");
	}

}
