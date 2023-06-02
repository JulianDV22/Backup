package co.edu.uco.teqvim.business.business.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.teqvim.business.assembler.concrete.RespuestaAssembler;
import co.edu.uco.teqvim.business.business.RespuestaBusiness;
import co.edu.uco.teqvim.business.domain.EstadoEstudianteDomain;
import co.edu.uco.teqvim.business.domain.RespuestaDomain;
import co.edu.uco.teqvim.crosscutting.exception.TeqvimBusinessException;
import co.edu.uco.teqvim.crosscutting.utils.UtilUUID;
import co.edu.uco.teqvim.data.dao.factory.DAOFactory;
import co.edu.uco.teqvim.entities.EstadoEstudianteEntity;
import co.edu.uco.teqvim.entities.RespuestaEntity;

public final class RespuestaBusinessImpl implements RespuestaBusiness {

	private DAOFactory daoFactory;

	public RespuestaBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<RespuestaDomain> list(final RespuestaDomain domain) {
		final RespuestaEntity entity = RespuestaAssembler.getInstance().toEntityFromDomain(domain);

		List<RespuestaEntity> resultEntityList = daoFactory.getRespuestaDAO().read(entity);

		return RespuestaAssembler.getInstance().toDomainListFromEntityList(resultEntityList);
	}

	@Override
	public void register(RespuestaDomain domain) {

		UUID identificador;
		RespuestaEntity entityTmp;
		List<RespuestaEntity> result;

		do {
			identificador = UtilUUID.generateNewUUID();
			entityTmp = RespuestaEntity.createWithIdentificador(identificador);
			result = daoFactory.getRespuestaDAO().read(entityTmp);
		} while (!result.isEmpty());

		entityTmp = RespuestaEntity.createWithNombre(domain.getNombre());
		result = daoFactory.getRespuestaDAO().read(entityTmp);

		if (!result.isEmpty()) {

			throw TeqvimBusinessException
					.create("La respuesta que intenta crear ya existe, Por favor verifique los datos");
		}

		final var domainToCreate = new RespuestaDomain(identificador, domain.getNombre(), domain.getDescripcion());

		final RespuestaEntity entity = RespuestaAssembler.getInstance().toEntityFromDomain(domainToCreate);
		daoFactory.getRespuestaDAO().create(entity);

	}

}
