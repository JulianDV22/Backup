package co.edu.uco.teqvim.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.teqvim.data.dao.TipoDocumentoDAO;
import co.edu.uco.teqvim.entities.TipoDocumentoEntity;

public final class TipoDocumentoPostgreSqlDAO implements TipoDocumentoDAO {

	public TipoDocumentoPostgreSqlDAO(final Connection connection) {

	}
	@Override
	public List<TipoDocumentoEntity> read(TipoDocumentoEntity entity) {
		return null;
	}

}
