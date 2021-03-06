package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.WorkplaceCandidate;

public interface WorkplaceCandidateDao extends JpaRepository<WorkplaceCandidate, Integer> {

	List<WorkplaceCandidate> getByCandidate_Id(int candidateId);
	List<WorkplaceCandidate> getByCandidate_IdOrderByDateOfQuitDesc(int candidateId);
}
