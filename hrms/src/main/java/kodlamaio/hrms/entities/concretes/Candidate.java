package kodlamaio.hrms.entities.concretes;


import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import kodlamaio.hrms.entities.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "candidates")
public class Candidate extends User {

	public Candidate(String firstName, String lastName, String nationalIdentity, Date dateOfBirth, String email, String password)
    {
        super(email,password);
        this.firstName=firstName;
        this.lastName=lastName;
        this.nationalIdentity=nationalIdentity;
        this.dateOfBirth=dateOfBirth;
    }
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="national_identity")
	private String nationalIdentity;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
}
