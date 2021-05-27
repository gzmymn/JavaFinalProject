package kodlamaio.hrms.business.concretes.auth;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.business.abstracts.auth.VerifyService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.ActivationCode;

@Service
public class VerifyManager implements VerifyService {

	private ActivationCodeService activationCodeService;
	
	public VerifyManager(ActivationCodeService activationCodeService) {
		
		this.activationCodeService = activationCodeService;
	}

	@Override
	public Result verify(int userId, String activationCode) {

		Optional<ActivationCode> activation = activationCodeService.getByUserId(userId).getData();
		Result result = subVerify(activation, activationCode);
		if (!result.isSuccess()) {
			return result;
		}
		activation.get().setActivationDate(LocalDateTime.now());
		activation.get().setConfirmed(true);
		activationCodeService.update(activation.get());
		return new SuccessResult("Kod doğrulandı.");
	}
	
	private Result subVerify(Optional<ActivationCode> activation, String activationCode) {
		
		if (activation.isEmpty()) {
			return new ErrorResult("Kod bulunamadı.");
		}
		
		if (activation.get().isConfirmed()) {
			return new ErrorResult("Kod mevcut.");
		}
		
		if (activation.get().getExprationDate().isBefore(LocalDateTime.now())) {
			return new ErrorResult("Kodun süresi doldu.");
		}
		
		if (!activation.get().getActivationCode().equals(activationCode)) {
			return new ErrorResult("Kodlar eşleşmedi.");
		}
		
		return new SuccessResult();
	}

}
