package co.edu.uco.teqvim.business.business;

import java.util.List;

import co.edu.uco.teqvim.business.domain.PaisDomain;

public interface PaisBusiness {
	
	void register(PaisDomain domain);

	List<PaisDomain> list(PaisDomain domain);

}
