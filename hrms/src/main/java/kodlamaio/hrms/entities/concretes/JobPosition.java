package kodlamaio.hrms.entities.concretes;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="job_positions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","jobPostings"})
public class JobPosition {	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="id")
	private int id;
	
	@Column(name="position")
	private String position;
		
	@Column(name = "created_date")
	private LocalDateTime createdDate=LocalDateTime.now();
		
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;
		
	@Column(name = "uid")
	private String uid;
	
	@Column(name= "is_deleted")
    private boolean isDeleted=false;

    @Column(name="is_activated")
    private boolean isActivated;
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobPosition")
	private List<JobPosting> jobPostings;

}
