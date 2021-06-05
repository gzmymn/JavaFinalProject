package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LinkTypeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LinkTypeDao;
import kodlamaio.hrms.entities.concretes.LinkType;

@Service
public class LinkTypeManager implements LinkTypeService {

	private LinkTypeDao linkTypeDao;
	
	@Autowired
	public LinkTypeManager(LinkTypeDao linkTypeDao) {
		
		this.linkTypeDao = linkTypeDao;
	}

	@Override
	public DataResult<List<LinkType>> getAll() {
		
		return new SuccessDataResult<List<LinkType>>(linkTypeDao.findAll(), "link tipleri listelendi");
	}

	@Override
	public Result add(LinkType linkType) {
		linkTypeDao.save(linkType);
		return new SuccessResult("Link tipi eklendi.");
	}

}
