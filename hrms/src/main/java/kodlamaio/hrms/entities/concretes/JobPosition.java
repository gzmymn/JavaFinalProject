package kodlamaio.hrms.entities.concretes;

import java.sql.Date;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="job_positions")
public class JobPosition {
	
	public JobPosition(String position) {
		super();
		this.position=position;
	}
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String position;
	
	@CreatedDate
	@Column(name = "created_date")
	private Date createdDate;
	
	@LastModifiedDate
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "status")
	private boolean status;

}
