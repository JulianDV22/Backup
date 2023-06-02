package co.edu.uco.teqvim.business.business.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.teqvim.business.assembler.concrete.TipoDuracionEventoAssembler;
import co.edu.uco.teqvim.business.business.TipoDuracionEventoBusiness;
import co.edu.uco.teqvim.business.domain.EstadoEstudianteDomain;
import co.edu.uco.teqvim.business.domain.TipoDuracionEventoDomain;
import co.edu.uco.teqvim.crosscutting.exception.TeqvimBusinessException;
import co.edu.uco.teqvim.crosscutting.utils.UtilUUID;
import co.edu.uco.teqvim.data.dao.factory.DAOFactory;
import co.edu.uco.teqvim.entities.EstadoEstudianteEntity;
import co.edu.uco.teqvim.entities.TipoDuracionEventoEntity;

public final class TipoDuracionEventoBusinessImpl implements TipoDuracionEventoBusiness {

	private DAOFactory daoFactory;

	public TipoDuracionEventoBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<TipoDuracionEventoDomain> list(final TipoDuracionEventoDomain domain) {
		final TipoDuracionEventoEntity entity = TipoDuracionEventoAssembler.getInstance().toEntityFromDomain(domain);

		List<TipoDuracionEventoEntity> resultEntityList = daoFactory.getTipoDuracionEventoDAO().read(entity);

		return TipoDuracionEventoAssembler.getInstance().toDomainListFromEntityList(resultEntityList);
	}

	@Override
	public void register(TipoDuracionEventoDomain domain) {

		UUID identificador;
		TipoDuracionEventoEntity entityTmp;
		List<TipoDuracionEventoEntity> result;

		do {
			identificador = UtilUUID.generateNewUUID();
			entityTmp = TipoDuracionEventoEntity.createWithIdentificador(identificador);
			result = daoFactory.getTipoDuracionEventoDAO().read(entityTmp);
		} while (!result.isEmpty());

		entityTmp = TipoDuracionEventoEntity.createWithNombre(domain.getNombre());
		result = daoFactory.getTipoDuracionEventoDAO().read(entityTmp);

		if (!result.isEmpty()) {

			throw TeqvimBusinessException.create(
					"El tipo de duracion del evento que intenta crear ya existe, Por favor verifique los datos");
		}

		final var domainToCreate = new TipoDuracionEventoDomain(identificador, domain.getNombre(),
				domain.getDescripcion());

		final TipoDuracionEventoEntity entity = TipoDuracionEventoAssembler.getInstance()
				.toEntityFromDomain(domainToCreate);
		daoFactory.getTipoDuracionEventoDAO().create(entity);

	}

}
