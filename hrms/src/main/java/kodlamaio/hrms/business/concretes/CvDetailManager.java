package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CvDetailService;
import kodlamaio.hrms.core.utilities.helpers.ImageUpload;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvDetailDao;
import kodlamaio.hrms.entities.concretes.CvDetail;

@Service
public class CvDetailManager implements CvDetailService {

	private CvDetailDao cvDetailDao;
	private ImageUpload imageUpload;
	
	@Autowired
	public CvDetailManager(CvDetailDao cvDetailDao, ImageUpload imageUpload) {
		
		this.cvDetailDao = cvDetailDao;
		this.imageUpload = imageUpload;
	}

	@Override
	public DataResult<List<CvDetail>> getAll() {
		
		return new SuccessDataResult<List<CvDetail>>(cvDetailDao.findAll());
	}

	@Override
	public DataResult<CvDetail> getByCandidateId(int candidateId) {
		
		return new SuccessDataResult<CvDetail>(cvDetailDao.getByCandidate_Id(candidateId));
	}

	@Override
	public Result add(CvDetail cvDetail) {
		cvDetailDao.save(cvDetail);
		return new SuccessResult("Cv detayları eklendi.");
	}

	@Override
	public Result uploadPhoto(MultipartFile file, int candidate) {
		CvDetail cvDetail=getByCandidateId(candidate).getData();
		Map<String, String> result = (Map<String, String>) imageUpload.upload(file).getData();
		String url = result.get("url");
		cvDetail.setImageUrl(url);
		cvDetailDao.save(cvDetail);
		return new SuccessResult("Fotoğraf yükleme başarılı");
	}

}
