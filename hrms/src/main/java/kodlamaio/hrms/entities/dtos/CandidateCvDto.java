package kodlamaio.hrms.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kodlamaio.hrms.entities.abstracts.DtoOther;
import kodlamaio.hrms.entities.concretes.AbilityCandidate;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CvDetail;
import kodlamaio.hrms.entities.concretes.LanguageCandidate;
import kodlamaio.hrms.entities.concretes.SchoolCandidate;
import kodlamaio.hrms.entities.concretes.SocialMedia;
import kodlamaio.hrms.entities.concretes.WorkplaceCandidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateCvDto implements DtoOther {

	@JsonIgnore
	private Candidate candidate;
	
	private List<AbilityCandidate> abilityCandidates;
	private List<LanguageCandidate> languageCandidates;
	private List<SchoolCandidate> schoolCandidates;
	private List<SocialMedia> socialMedias;
	private List<WorkplaceCandidate> workplaceCandidates;
	private CvDetail cvDetail;
	
}
