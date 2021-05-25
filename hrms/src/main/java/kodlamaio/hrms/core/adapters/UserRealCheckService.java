package kodlamaio.hrms.core.adapters;

import kodlamaio.hrms.core.adapters.models.MernisPerson;

public interface UserRealCheckService {
	
	boolean validate(MernisPerson person);
}
