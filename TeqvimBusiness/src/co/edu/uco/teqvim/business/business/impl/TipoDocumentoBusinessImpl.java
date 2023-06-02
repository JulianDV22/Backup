package co.edu.uco.teqvim.business.business.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.teqvim.business.assembler.concrete.TipoDocumentoAssembler;
import co.edu.uco.teqvim.business.business.TipoDocumentoBusiness;
import co.edu.uco.teqvim.business.domain.EstadoEstudianteDomain;
import co.edu.uco.teqvim.business.domain.TipoDocumentoDomain;
import co.edu.uco.teqvim.crosscutting.exception.TeqvimBusinessException;
import co.edu.uco.teqvim.crosscutting.utils.UtilUUID;
import co.edu.uco.teqvim.data.dao.factory.DAOFactory;
import co.edu.uco.teqvim.entities.EstadoEstudianteEntity;
import co.edu.uco.teqvim.entities.TipoDocumentoEntity;

public final class TipoDocumentoBusinessImpl implements TipoDocumentoBusiness {

	private DAOFactory daoFactory;

	public TipoDocumentoBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<TipoDocumentoDomain> list(final TipoDocumentoDomain domain) {
		final TipoDocumentoEntity entity = TipoDocumentoAssembler.getInstance().toEntityFromDomain(domain);

		List<TipoDocumentoEntity> resultEntityList = daoFactory.getTipoDocumentoDAO().read(entity);

		return TipoDocumentoAssembler.getInstance().toDomainListFromEntityList(resultEntityList);
	}

	@Override
	public void register(TipoDocumentoDomain domain) {

		UUID identificador;
		TipoDocumentoEntity entityTmp;
		List<TipoDocumentoEntity> result;

		do {
			identificador = UtilUUID.generateNewUUID();
			entityTmp = TipoDocumentoEntity.createWithIdentificador(identificador);
			result = daoFactory.getTipoDocumentoDAO().read(entityTmp);
		} while (!result.isEmpty());

		entityTmp = TipoDocumentoEntity.createWithNombre(domain.getNombre());
		result = daoFactory.getTipoDocumentoDAO().read(entityTmp);

		if (!result.isEmpty()) {

			throw TeqvimBusinessException
					.create("El tipo de documento que intenta crear ya existe, Por favor verifique los datos");
		}

		final var domainToCreate = new TipoDocumentoDomain(identificador, domain.getNombre(), domain.getDescripcion());

		final TipoDocumentoEntity entity = TipoDocumentoAssembler.getInstance().toEntityFromDomain(domainToCreate);
		daoFactory.getTipoDocumentoDAO().create(entity);

	}

}
