package co.edu.uco.teqvim.business.business.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.teqvim.business.assembler.concrete.EstadoEstudianteAssembler;
import co.edu.uco.teqvim.business.business.EstadoEstudianteBusiness;
import co.edu.uco.teqvim.business.domain.EstadoEstudianteDomain;
import co.edu.uco.teqvim.business.domain.EventoDomain;
import co.edu.uco.teqvim.crosscutting.exception.TeqvimBusinessException;
import co.edu.uco.teqvim.crosscutting.utils.UtilUUID;
import co.edu.uco.teqvim.data.dao.factory.DAOFactory;
import co.edu.uco.teqvim.entities.EstadoEstudianteEntity;
import co.edu.uco.teqvim.entities.EventoEntity;

public final class EstadoEstudianteBusinessImpl implements EstadoEstudianteBusiness {

	private DAOFactory daoFactory;

	public EstadoEstudianteBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<EstadoEstudianteDomain> list(final EstadoEstudianteDomain domain) {
		final EstadoEstudianteEntity entity = EstadoEstudianteAssembler.getInstance().toEntityFromDomain(domain);

		List<EstadoEstudianteEntity> resultEntityList = daoFactory.getEstadoEstudianteDAO().read(entity);

		return EstadoEstudianteAssembler.getInstance().toDomainListFromEntityList(resultEntityList);
	}

	@Override
	public void register(EstadoEstudianteDomain domain) {

		UUID identificador;
		EstadoEstudianteEntity entityTmp;
		List<EstadoEstudianteEntity> result;

		do {
			identificador = UtilUUID.generateNewUUID();
			entityTmp = EstadoEstudianteEntity.createWithIdentificador(identificador);
			result = daoFactory.getEstadoEstudianteDAO().read(entityTmp);
		} while (!result.isEmpty());

		entityTmp = EstadoEstudianteEntity.createWithNombre(domain.getNombre());
		result = daoFactory.getEstadoEstudianteDAO().read(entityTmp);

		if (!result.isEmpty()) {

			throw TeqvimBusinessException
					.create("El estado del estudiante que intenta crear ya existe, Por favor verifique los datos");
		}

		final var domainToCreate = new EstadoEstudianteDomain(identificador, domain.getNombre(),
				domain.getDescripcion());

		final EstadoEstudianteEntity entity = EstadoEstudianteAssembler.getInstance()
				.toEntityFromDomain(domainToCreate);
		daoFactory.getEstadoEstudianteDAO().create(entity);

	}

}
