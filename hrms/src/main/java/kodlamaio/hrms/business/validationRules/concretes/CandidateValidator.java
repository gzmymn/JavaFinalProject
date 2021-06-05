package kodlamaio.hrms.business.validationRules.concretes;

import java.sql.Date;
import org.springframework.stereotype.Component;
import com.google.common.base.Strings;
import kodlamaio.hrms.business.validationRules.abstracts.CandidateValidatorService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.Candidate;

@Component
public class CandidateValidator extends UserValidator implements CandidateValidatorService {
	
	@Override
	public Result candidateNullCheck(Candidate candidate) {
		
		String firstName=candidate.getFirstName();
		String lastName=candidate.getLastName();
		String tcNo=candidate.getNationalIdentity();
		Date birthYear=candidate.getDateOfBirth();
		
		//if (super.userNullCheck(candidate).isSuccess() && (firstName==null || firstName.isBlank()) 
		//		&& (lastName==null || lastName.isBlank()) 
		//		&& (tcNo==null || tcNo.isBlank())
		//		&& (birthYear==null)) {			
		//	return new ErrorResult("Değerler boş bırakılamaz!");
		//}
		//return new SuccessResult();
		
		if (!super.userNullCheck(candidate).isSuccess() || (Strings.isNullOrEmpty(firstName)) ||
                (Strings.isNullOrEmpty(lastName)) || (Strings.isNullOrEmpty(tcNo)) || birthYear == null) {
            return new ErrorResult("Alanlar boş bırakılamaz!");
        }
        return new SuccessResult();
	}

	@Override
	public Result nationalIdValid(String nationalIdentity) {
		if (nationalIdentity.length()==11) {
			return new SuccessResult();
		}
		return new ErrorResult("Tc No geçersiz!");
	}

}
