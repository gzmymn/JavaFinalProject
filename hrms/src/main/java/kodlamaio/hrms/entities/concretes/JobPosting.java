package kodlamaio.hrms.entities.concretes;

import java.sql.Date;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="job_postings")
public class JobPosting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "uid")
	private String uid;
	
	@Column(name = "job_definition", length=3000)
	@Type(type = "text")
	private String jobDefinition;
	
	@Column(name = "number_of_open_position")
	private int numberOfOpenPosition;
	
	@Column(name = "created_date")
	private LocalDateTime createdDate=LocalDateTime.now();;
	
	@Column(name = "application_deadline")
	private LocalDateTime applicationDeadline;
	
	@Column(name= "is_deleted")
    private boolean isDeleted=false;

    @Column(name="is_activated")
    private boolean isActivated;
    
    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;
	
	@Column(name="min_salary")
	private Double minSalary;
	
	@Column(name="max_salary")
	private Double maxSalary;
	
	
	@ManyToOne()
    @JoinColumn(name="employer_id")
    private Employer employer;	
	
	
	@ManyToOne()
	@JoinColumn(name="job_position_id")
	private JobPosition jobPosition;
	
	
	@ManyToOne()
	@JoinColumn(name="city_id")
	private City city;
}
