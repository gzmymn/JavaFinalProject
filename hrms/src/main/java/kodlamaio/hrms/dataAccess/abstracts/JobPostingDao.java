package kodlamaio.hrms.dataAccess.abstracts;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.JobPosting;
import kodlamaio.hrms.entities.dtos.JobPostingDetailsDto;

public interface JobPostingDao extends JpaRepository<JobPosting, Integer> {

	List<JobPosting> getByIsActivatedTrueOrderByCreatedDateDesc();
	List<JobPosting> getByCreatedDateBetweenAndIsActivatedTrueOrderByCreatedDateDesc(LocalDateTime startDate, LocalDateTime finishDate);
	List<JobPosting> getByEmployer_IdAndIsActivatedTrue(int employerId);
	JobPosting getById(int id);
	
	@Query("select new kodlamaio.hrms.entities.dtos.JobPostingDetailsDto(e.companyName, p.position, j.quota, c.cityName, j.applicationDeadline, j.createdDate) from JobPosting j inner join j.employer e inner join j.jobPosition p inner join j.city c ")
	List<JobPostingDetailsDto> getJobPostingDetails();
}
