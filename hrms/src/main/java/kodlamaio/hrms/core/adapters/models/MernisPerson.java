package kodlamaio.hrms.core.adapters.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MernisPerson {

	private String firstName;
	private String lastName;
	private String identificationNumber;
	private LocalDate birthyear;
}
