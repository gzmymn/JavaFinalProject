package kodlamaio.hrms.dataAccess.abstracts;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobPosting;

public interface JobPostingDao extends JpaRepository<JobPosting, Integer> {

	List<JobPosting> getByIsActivatedTrueOrderByCreatedDateDesc();
	List<JobPosting> getByCreatedDateBetweenAndIsActivatedTrueOrderByCreatedDateDesc(LocalDateTime startDate, LocalDateTime finishDate);
	List<JobPosting> getByEmployer_IdAndIsActivatedTrue(int employerId);
	JobPosting getById(int id);
}
