package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForCandidateDto {

	private String firstName;
	private String lastName;
	private String nationalIdentity;
	private LocalDate dateOfBirth;
	private String email;	
	private String password;
	private String confirmPassword;
}
