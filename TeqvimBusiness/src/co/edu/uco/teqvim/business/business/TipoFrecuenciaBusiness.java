package co.edu.uco.teqvim.business.business;

import java.util.List;
import co.edu.uco.teqvim.business.domain.TipoFrecuenciaDomain;

public interface TipoFrecuenciaBusiness {

	void register(TipoFrecuenciaDomain domain);

	List<TipoFrecuenciaDomain> list(TipoFrecuenciaDomain domain);

}
