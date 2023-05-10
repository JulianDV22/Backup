package co.edu.uco.teqvim.business.facade;

import java.util.List;
import co.edu.uco.teqvim.dto.TipoFrecuenciaDTO;

public interface TipoFrecuenciaFacade {

	void register(TipoFrecuenciaDTO dto);

	List<TipoFrecuenciaDTO> list(TipoFrecuenciaDTO dto);

}
