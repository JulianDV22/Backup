package co.edu.uco.teqvim.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.teqvim.crosscutting.utils.UtilObject;
import co.edu.uco.teqvim.crosscutting.utils.UtilText;
import co.edu.uco.teqvim.crosscutting.utils.UtilUUID;
import co.edu.uco.teqvim.data.dao.EstadoEventoDAO;
import co.edu.uco.teqvim.data.dao.relational.SqlDAO;
import co.edu.uco.teqvim.entities.EstadoEventoEntity;

public final class EstadoEventoPostgreSqlDAO extends SqlDAO<EstadoEventoEntity> implements EstadoEventoDAO {

	public EstadoEventoPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public List<EstadoEventoEntity> read(EstadoEventoEntity entity) {
		return null;
	}

	@Override
	protected String prepareSelect() {
		return "SELECT es.identificador, es.nombre, es.descripcion, ce.identificador, ce.nombre ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM estado es JOIN clase_estado ce ON es.identificador = ce.identificador";
	}

	@Override
	protected String prepareWhere(EstadoEventoEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());

		var setWhere = true;

		if (UtilObject.isNull(entity)) {
			if (!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if (!UtilText.getUtilText().isEmpty(entity.getNombre())) {
				parameters.add(entity.getNombre());
				where.append(setWhere ? "WHERE " : "AND").append("nombre=? ");
				setWhere = false;
			}
			if (!UtilText.getUtilText().isEmpty(entity.getDescripcion())) {
				parameters.add(entity.getDescripcion());
				where.append(setWhere ? "WHERE " : "AND").append("descripcion LIKE %?% ");
			}
		}
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setParameters(PreparedStatement preparedStatement, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<EstadoEventoEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}

}
