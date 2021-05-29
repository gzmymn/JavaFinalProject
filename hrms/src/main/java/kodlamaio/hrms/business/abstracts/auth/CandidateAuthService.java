package kodlamaio.hrms.business.abstracts.auth;

import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.dtos.RegisterForCandidateDto;

public interface CandidateAuthService extends UserAuthService<RegisterForCandidateDto, Candidate>{
	
	
}
