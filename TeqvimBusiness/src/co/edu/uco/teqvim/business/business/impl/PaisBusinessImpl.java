package co.edu.uco.teqvim.business.business.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.teqvim.business.assembler.concrete.PaisAssembler;
import co.edu.uco.teqvim.business.business.PaisBusiness;
import co.edu.uco.teqvim.business.domain.EstadoEstudianteDomain;
import co.edu.uco.teqvim.business.domain.PaisDomain;
import co.edu.uco.teqvim.crosscutting.exception.TeqvimBusinessException;
import co.edu.uco.teqvim.crosscutting.utils.UtilUUID;
import co.edu.uco.teqvim.data.dao.factory.DAOFactory;
import co.edu.uco.teqvim.entities.EstadoEstudianteEntity;
import co.edu.uco.teqvim.entities.PaisEntity;

public final class PaisBusinessImpl implements PaisBusiness {

	private DAOFactory daoFactory;

	public PaisBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<PaisDomain> list(final PaisDomain domain) {
		final PaisEntity entity = PaisAssembler.getInstance().toEntityFromDomain(domain);

		List<PaisEntity> resultEntityList = daoFactory.getPaisDAO().read(entity);

		return PaisAssembler.getInstance().toDomainListFromEntityList(resultEntityList);
	}

	@Override
	public void register(PaisDomain domain) {

		UUID identificador;
		PaisEntity entityTmp;
		List<PaisEntity> result;

		do {
			identificador = UtilUUID.generateNewUUID();
			entityTmp = PaisEntity.createWithIdentificador(identificador);
			result = daoFactory.getPaisDAO().read(entityTmp);
		} while (!result.isEmpty());

		entityTmp = PaisEntity.createWithNombre(domain.getNombre());
		result = daoFactory.getPaisDAO().read(entityTmp);

		if (!result.isEmpty()) {

			throw TeqvimBusinessException
					.create("El pais que intenta crear ya existe, Por favor verifique los datos");
		}

		final var domainToCreate = new PaisDomain(identificador, domain.getNombre());

		final PaisEntity entity = PaisAssembler.getInstance().toEntityFromDomain(domainToCreate);
		daoFactory.getPaisDAO().create(entity);

	}

}
