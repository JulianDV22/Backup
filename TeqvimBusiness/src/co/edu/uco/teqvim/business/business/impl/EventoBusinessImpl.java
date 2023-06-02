package co.edu.uco.teqvim.business.business.impl;

import java.util.List;
import java.util.UUID;

import co.edu.uco.teqvim.business.assembler.concrete.EventoAssembler;
import co.edu.uco.teqvim.business.business.EventoBusiness;
import co.edu.uco.teqvim.business.domain.EventoDomain;
import co.edu.uco.teqvim.crosscutting.exception.TeqvimBusinessException;
import co.edu.uco.teqvim.crosscutting.utils.UtilUUID;
import co.edu.uco.teqvim.data.dao.factory.DAOFactory;
import co.edu.uco.teqvim.entities.EventoEntity;

public final class EventoBusinessImpl implements EventoBusiness {

	private DAOFactory daoFactory;

	public EventoBusinessImpl(final DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void register(final EventoDomain domain) {

		UUID identificador;
		EventoEntity entityTmp;
		List<EventoEntity> result;

		do {
			identificador = UtilUUID.generateNewUUID();
			entityTmp = EventoEntity.createWithIdentificador(identificador);
			result = daoFactory.getEventoDAO().read(entityTmp);
		} while (!result.isEmpty());

		entityTmp = EventoEntity.createWithTitulo(domain.getTitulo());
		result = daoFactory.getEventoDAO().read(entityTmp);

		if (!result.isEmpty()) {

			throw TeqvimBusinessException
					.create("El evento que intenta crear ya existe, Por favor verifique los datos");
		}
		
		final var domainToCreate = new EventoDomain(identificador, domain.getTitulo(), domain.getDescripcion(),
				domain.getFechaInicio(), domain.getFechaFin(), domain.getDuracionEvento(), domain.getEstado(),
				domain.getTipoEvento(), domain.getEstudiante());

		final EventoEntity entity = EventoAssembler.getInstance().toEntityFromDomain(domainToCreate);
		daoFactory.getEventoDAO().create(entity);

	}

	@Override
	public List<EventoDomain> list(final EventoDomain domain) {
		final EventoEntity entity = EventoAssembler.getInstance().toEntityFromDomain(domain);

		List<EventoEntity> resultEntityList = daoFactory.getEventoDAO().read(entity);

		return EventoAssembler.getInstance().toDomainListFromEntityList(resultEntityList);
	}

	@Override
	public void modify(final EventoDomain domain) {
		final EventoEntity entity = EventoAssembler.getInstance().toEntityFromDomain(domain);
		daoFactory.getEventoDAO().update(entity);

	}

	@Override
	public void drop(final EventoDomain domain) {
		final EventoEntity entity = EventoAssembler.getInstance().toEntityFromDomain(domain);
		daoFactory.getEventoDAO().delete(entity);

	}

}
