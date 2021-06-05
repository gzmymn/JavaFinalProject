package kodlamaio.hrms.entities.concretes;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kodlamaio.hrms.entities.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "candidates")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","schoolCandidates","languageCandiates","abilityCandidates","workplaceCandidates","socialMedias","cvDetail"})
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
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<AbilityCandidate> abilityCandidates;
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<CvDetail> cvDetails;
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<LanguageCandidate> languageCandidates;
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<SchoolCandidate> schoolCandidates;
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<SocialMedia> socialMedias;
	
	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<WorkplaceCandidate> workplaceCandidates;
}
