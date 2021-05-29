package kodlamaio.hrms.entities.dtos;

import java.sql.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import kodlamaio.hrms.entities.abstracts.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForCandidateDto extends Dto {

	@Size(min=2, message="İsim en az 2 karakter olmalıdır.")	
	private String firstName;
	
	@Size(min=2, message="Soyisim en az 2 karakter olmalıdır.")	
	private String lastName;
	
	@Pattern(regexp = "^[1-9]{1}[0-9]{9}[02468]{1}$", message = "Tc Kimlik No çift sayı ile bitmeli.")
	private String nationalIdentity;
	
	private Date dateOfBirth;
	
}
