package kodlamaio.hrms.entities.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import kodlamaio.hrms.entities.abstracts.DtoOther;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobPostingDetailsDto implements DtoOther {

	private String companyName;
	private String jobPosition;
	private int quota;
	private String cityName;
	private LocalDate applicationDeadline;
	private LocalDateTime createdDate;
}
