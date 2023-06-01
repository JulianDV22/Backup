package co.edu.uco.teqvim.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import co.edu.uco.teqvim.data.dao.EstadoEstudianteDAO;
import co.edu.uco.teqvim.data.dao.relational.SqlDAO;
import co.edu.uco.teqvim.entities.EstadoEstudianteEntity;

public final class EstadoEstudiantePostgreSqlDAO extends SqlDAO<EstadoEstudianteDAO> implements EstadoEstudianteDAO {

	public EstadoEstudiantePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public List<EstadoEstudianteEntity> read(EstadoEstudianteEntity entity) {
		return null;
	}

	@Override
	protected String prepareSelect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String prepareFrom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String prepareWhere(EstadoEstudianteDAO entity, List<Object> parameters) {
		// TODO Auto-generated method stub
		return null;
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
	protected List<EstadoEstudianteDAO> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}

}
