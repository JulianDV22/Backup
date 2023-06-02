package co.edu.uco.teqvim.business.business.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.teqvim.business.assembler.concrete.TipoEventoAssembler;
import co.edu.uco.teqvim.business.business.TipoEventoBusiness;
import co.edu.uco.teqvim.business.domain.EstadoEstudianteDomain;
import co.edu.uco.teqvim.business.domain.TipoEventoDomain;
import co.edu.uco.teqvim.crosscutting.exception.TeqvimBusinessException;
import co.edu.uco.teqvim.crosscutting.utils.UtilUUID;
import co.edu.uco.teqvim.data.dao.factory.DAOFactory;
import co.edu.uco.teqvim.entities.EstadoEstudianteEntity;
import co.edu.uco.teqvim.entities.TipoEventoEntity;

public final class TipoEventoBusinessImpl implements TipoEventoBusiness {

	private DAOFactory daoFactory;

	public TipoEventoBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<TipoEventoDomain> list(final TipoEventoDomain domain) {
		final TipoEventoEntity entity = TipoEventoAssembler.getInstance().toEntityFromDomain(domain);

		List<TipoEventoEntity> resultEntityList = daoFactory.getTipoEventoDAO().read(entity);

		return TipoEventoAssembler.getInstance().toDomainListFromEntityList(resultEntityList);
	}

	@Override
	public void register(TipoEventoDomain domain) {

		UUID identificador;
		TipoEventoEntity entityTmp;
		List<TipoEventoEntity> result;

		do {
			identificador = UtilUUID.generateNewUUID();
			entityTmp = TipoEventoEntity.createWithIdentificador(identificador);
			result = daoFactory.getTipoEventoDAO().read(entityTmp);
		} while (!result.isEmpty());

		entityTmp = TipoEventoEntity.createWithNombre(domain.getNombre());
		result = daoFactory.getTipoEventoDAO().read(entityTmp);

		if (!result.isEmpty()) {

			throw TeqvimBusinessException
					.create("El estado del estudiante que intenta crear ya existe, Por favor verifique los datos");
		}

		final var domainToCreate = new TipoEventoDomain(identificador, domain.getNombre(), domain.getDescripcion());

		final TipoEventoEntity entity = TipoEventoAssembler.getInstance().toEntityFromDomain(domainToCreate);
		daoFactory.getTipoEventoDAO().create(entity);

	}

}
