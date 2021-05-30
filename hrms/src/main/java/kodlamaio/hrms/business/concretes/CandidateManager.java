package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.validationRules.abstracts.CandidateValidatorService;
import kodlamaio.hrms.core.adapters.UserRealCheckService;
import kodlamaio.hrms.core.adapters.models.MernisPerson;
import kodlamaio.hrms.core.utilities.business.BusinessEngine;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager extends UserManager<Candidate>  implements CandidateService {

	private final CandidateDao candidateDao;
	private final UserRealCheckService userRealCheckService;
	private final CandidateValidatorService candidateValidatorService;
	
	@Autowired
	public CandidateManager(UserDao<Candidate> userDao, UserRealCheckService userRealCheckService, CandidateValidatorService candidateValidatorService) {
		super(userDao);
		this.candidateDao=(CandidateDao) userDao;
		this.userRealCheckService=userRealCheckService;
		this.candidateValidatorService=candidateValidatorService;		
	}
	
	@Override
    public Result add(Candidate candidate) {
		
		Result result = BusinessEngine.run(isIdentityNumberExist(candidate.getNationalIdentity()),
                isMernisVerified(candidate),candidateValidatorService.candidateNullCheck(candidate),
                candidateValidatorService.nationalIdValid(candidate.getNationalIdentity()));
        if (!result.isSuccess()) {
            
        	return result;
        }        
        return super.add(candidate);
    }
	
    private Result isIdentityNumberExist(String identityNumber) {
        if (candidateDao.findByNationalIdentity(identityNumber).isPresent()) {
            return new ErrorResult("Kimlik numarası zaten mevcut!");
        }
        return new SuccessResult();
    }
    
    private Result isMernisVerified(Candidate candidate) {
        MernisPerson mernisPerson = new MernisPerson(candidate.getFirstName(), candidate.getLastName(),
                candidate.getNationalIdentity(), candidate.getDateOfBirth());
        boolean result = userRealCheckService.validate(mernisPerson);
        
        if(result){
            return new SuccessResult();
        }      
        return new ErrorResult("Kişi doğrulaması yapılamadı!");
    }
	
}
