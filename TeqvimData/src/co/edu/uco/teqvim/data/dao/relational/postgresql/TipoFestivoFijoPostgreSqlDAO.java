package co.edu.uco.teqvim.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.teqvim.data.dao.TipoFestivoFijoDAO;
import co.edu.uco.teqvim.entities.TipoFestivoFijoEntity;

public final class TipoFestivoFijoPostgreSqlDAO implements TipoFestivoFijoDAO {

	public TipoFestivoFijoPostgreSqlDAO(final Connection connection) {

	}

	@Override
	public List<TipoFestivoFijoEntity> read(TipoFestivoFijoEntity entity) {
		return null;
	}

}
