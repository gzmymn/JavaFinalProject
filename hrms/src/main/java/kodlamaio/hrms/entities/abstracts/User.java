package kodlamaio.hrms.entities.abstracts;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "uid")
	private String uid;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;	
	
	@Column(name = "created_date")
	private LocalDateTime createdDate=LocalDateTime.now();
	
	@LastModifiedDate
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;
	
	@Column(name= "is_deleted")
    private boolean isDeleted;

    @Column(name="is_activated")
    private boolean isActivated;
}
