package kodlamaio.hrms.business.concretes.auth;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import kodlamaio.hrms.business.abstracts.ActivationCodeService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.business.abstracts.auth.UserAuthService;
import kodlamaio.hrms.business.validationRules.abstracts.AuthValidatorService;
import kodlamaio.hrms.core.utilities.business.BusinessEngine;
import kodlamaio.hrms.core.utilities.email.EmailSenderService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.core.utilities.verifyTool.CodeGenerator;
import kodlamaio.hrms.entities.abstracts.Dto;
import kodlamaio.hrms.entities.abstracts.User;
import kodlamaio.hrms.entities.concretes.ActivationCode;

public abstract class UserAuthManager<TDto extends Dto, TUser extends User> implements UserAuthService<TDto, TUser>  {

	private final AuthValidatorService authValidatorService;
	private final UserService<TUser> userService;
	private final ActivationCodeService activationCodeService;
    private final EmailSenderService emailSenderService;
    
    
	@Autowired
	public UserAuthManager(AuthValidatorService authValidatorService, UserService<TUser> userService, ActivationCodeService activationCodeService,
			EmailSenderService emailSenderService) {
		
		this.authValidatorService = authValidatorService;
		this.userService = userService;
		this.activationCodeService = activationCodeService;
		this.emailSenderService = emailSenderService;
	}

	@Override
	public Result register(TDto tDto) {

		Result result=BusinessEngine.run(authValidatorService.isPasswordConfirmed(tDto.getPassword(), tDto.getConfirmPassword()));
		if (!result.isSuccess()) {
			return result;
		}
		
		TUser user=newUserInstance(tDto);
		Result addResult=userService.add(user);
		if (!addResult.isSuccess()) {
			return addResult;
		}
		
		String code=CodeGenerator.generateUuidCode();
		Result codeAddResult=activationCodeAdd(user, code);
		if (!codeAddResult.isSuccess()) {
			return codeAddResult;
		}
		
		emailSenderService.send("Doğrulama için linke tıklayınız : http://localhost:8080/api/auth/verify?activationCode="+code+"&uid="+user.getUid());
        emailSenderService.send("Tekrar kod göndermek için linke tıklayınız : http://localhost:8080/api/auth/resend?uid="+user.getUid());
		
        return new SuccessResult("Kullanıcı eklendi.");
	}

	@Override
	public Result reSendEmail(String uid) {

		String activationCode=CodeGenerator.generateUuidCode();
		return activationCodeUpdate(uid, activationCode);
	}
	
	
	public abstract TUser newUserInstance(TDto tDto);
	
	private Result activationCodeAdd(User user, String code) {
		
		ActivationCode activationCode=new ActivationCode();
		activationCode.setActivationCode(code);
		activationCode.setUser(user);
		Result activationResult=activationCodeService.add(activationCode);
		
		if (!activationResult.isSuccess()) {
			return activationResult;
		}
		return new SuccessResult();
	}
	
	private Result activationCodeUpdate(String uid, String code) {
		
		Optional<ActivationCode> updateResult=activationCodeService.getByUserUid(uid).getData();
		if (updateResult.get().isConfirmed()) {
			return new ErrorResult("Kod oluşturulamıyor, öncelikle kullanıcının doğrulanması gerekmektedir.");	
					
		}
		
		updateResult.get().setExprationDate(LocalDateTime.now().plusMinutes(10));
		updateResult.get().setActivationCode(code);
		emailSenderService.send("Doğrulama için linke tıklayınız : http://localhost:8080/api/auth/verify?activationCode="+code+"&uid="+uid);
		Result activationResult=activationCodeService.update(updateResult.get());
		if (!activationResult.isSuccess()) {
			return activationResult;
		}
		return new SuccessResult();
	}
	

}
