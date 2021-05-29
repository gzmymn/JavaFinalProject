package kodlamaio.hrms.entities.dtos;

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
public class RegisterForEmployerDto extends Dto {

	private String companyName;
	private String webAddress;	
	
	@Pattern(regexp = "^(05)([0-9]{2})\\\\s?([0-9]{3})\\\\s?([0-9]{2})\\\\s?([0-9]{2})$" , message = "Telefon numarası 05xx xxx xx xx formatında olmalıdır.")
	@Size(min=11, max=11, message="11 haneli telefon numarası giriniz.")
	private String phoneNumber;
	
	
}
