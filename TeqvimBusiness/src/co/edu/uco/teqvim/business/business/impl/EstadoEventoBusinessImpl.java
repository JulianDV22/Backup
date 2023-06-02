package co.edu.uco.teqvim.business.business.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.teqvim.business.assembler.concrete.EstadoEventoAssembler;
import co.edu.uco.teqvim.business.business.EstadoEventoBusiness;
import co.edu.uco.teqvim.business.domain.EstadoEstudianteDomain;
import co.edu.uco.teqvim.business.domain.EstadoEventoDomain;
import co.edu.uco.teqvim.crosscutting.exception.TeqvimBusinessException;
import co.edu.uco.teqvim.crosscutting.utils.UtilUUID;
import co.edu.uco.teqvim.data.dao.factory.DAOFactory;
import co.edu.uco.teqvim.entities.EstadoEstudianteEntity;
import co.edu.uco.teqvim.entities.EstadoEventoEntity;

public final class EstadoEventoBusinessImpl implements EstadoEventoBusiness {

	private DAOFactory daoFactory;

	public EstadoEventoBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<EstadoEventoDomain> list(final EstadoEventoDomain domain) {
		final EstadoEventoEntity entity = EstadoEventoAssembler.getInstance().toEntityFromDomain(domain);

		List<EstadoEventoEntity> resultEntityList = daoFactory.getEstadoEventoDAO().read(entity);

		return EstadoEventoAssembler.getInstance().toDomainListFromEntityList(resultEntityList);
	}

	@Override
	public void register(EstadoEventoDomain domain) {
		
		UUID identificador;
		EstadoEventoEntity entityTmp;
		List<EEstadoEventoEntity> result;

		do {
			identificador = UtilUUID.generateNewUUID();
			entityTmp = EstadoEventoEntity.createWithIdentificador(identificador);
			result = daoFactory.getEstadoEventoDAO().read(entityTmp);
		} while (!result.isEmpty());

		entityTmp = EstadoEventoEntity.createWithNombre(domain.getNombre());
		result = daoFactory.getEstadoEventoDAO().read(entityTmp);

		if (!result.isEmpty()) {

			throw TeqvimBusinessException
					.create("El estado del estudiante que intenta crear ya existe, Por favor verifique los datos");
		}

		final var domainToCreate = new EstadoEstudianteDomain(identificador, domain.getNombre(),
				domain.getDescripcion());
		
		final EstadoEventoEntity entity = EstadoEventoAssembler.getInstance().toEntityFromDomain(domain);
		daoFactory.getEstadoEventoDAO().create(entity);
		
	}

}
