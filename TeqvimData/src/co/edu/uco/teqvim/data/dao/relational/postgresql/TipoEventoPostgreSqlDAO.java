package co.edu.uco.teqvim.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.teqvim.crosscutting.exception.TeqvimDataException;
import co.edu.uco.teqvim.crosscutting.exception.TeqvimException;
import co.edu.uco.teqvim.crosscutting.utils.UtilObject;
import co.edu.uco.teqvim.crosscutting.utils.UtilText;
import co.edu.uco.teqvim.crosscutting.utils.UtilUUID;
import co.edu.uco.teqvim.crosscutting.utils.Messages.TipoDocumentoSqlServerDAOMessages;
import co.edu.uco.teqvim.crosscutting.utils.Messages.TipoEventoSqlServerDAOMessages;
import co.edu.uco.teqvim.data.dao.TipoEventoDAO;
import co.edu.uco.teqvim.data.dao.relational.SqlDAO;
import co.edu.uco.teqvim.entities.TipoDocumentoEntity;
import co.edu.uco.teqvim.entities.TipoEventoEntity;

public final class TipoEventoPostgreSqlDAO extends SqlDAO<TipoEventoEntity> implements TipoEventoDAO {

	public TipoEventoPostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public List<TipoEventoEntity> read(TipoEventoEntity entity) {
		var sqlStatement = new StringBuilder();
		var parameters = new ArrayList<>();

		sqlStatement.append(prepareSelect());
		sqlStatement.append(prepareFrom());
		sqlStatement.append(prepareWhere(entity, parameters));
		sqlStatement.append(prepareOrderBy());

		try (var preparedStatement = getConnection().prepareStatement(sqlStatement.toString())) {

			setParameters(preparedStatement, parameters);

			return executeQuery(preparedStatement);

		} catch (final TeqvimException exception) {
			throw exception;
		} catch (SQLException exception) {
			var userMessage = TipoEventoSqlServerDAOMessages.READ_SQL_EXCEPTION_USER_MESSAGE;
			var technicalMessage = TipoEventoSqlServerDAOMessages.READ_SQL_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);
		} catch (Exception exception) {
			var userMessage = TipoEventoSqlServerDAOMessages.READ_EXCEPTION_USER_MESSAGE;
			var technicalMessage = TipoEventoSqlServerDAOMessages.READ_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);

		}
	}

	@Override
	public void create(TipoEventoEntity entity) {
		var sqlStatement = "INSERT INTO TipoEvento(identificador, nombre, descripcion) VALUES (?,?,?)";

		try (var PreparedStatement = getConnection().prepareStatement(sqlStatement)) {

			PreparedStatement.setObject(1, entity.getIdentificador());
			PreparedStatement.setString(2, entity.getNombre());
			PreparedStatement.setString(3, entity.getDescripcion());

			PreparedStatement.executeUpdate();

		} catch (SQLException exception) {
			var userMessage = TipoEventoSqlServerDAOMessages.CREATE_SQL_EXCEPTION_USER_MESSAGE;
			var technicalMessage = TipoEventoSqlServerDAOMessages.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);
		} catch (Exception exception) {
			var userMessage = TipoEventoSqlServerDAOMessages.CREATE_EXCEPTION_USER_MESSAGE;
			var technicalMessage = TipoEventoSqlServerDAOMessages.CREATE_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);

		}

	}

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, descripcion ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM TipoEvento ";
	}

	@Override
	protected String prepareWhere(TipoEventoEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;

		if (!UtilObject.isNull(entity)) {

			if (!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}

			if (!UtilText.getUtilText().isEmpty(entity.getNombre())) {
				parameters.add(entity.getNombre());
				where.append(setWhere ? "WHERE " : "AND ").append("nombre=? ");
				setWhere = false;
			}
			if (!UtilText.getUtilText().isEmpty(entity.getDescripcion())) {
				parameters.add(entity.getNombre());
				where.append(setWhere ? "WHERE " : "AND ").append("descripcion LIKE %?% ");
				setWhere = false;
			}

		}

		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY nombre ASC";
	}

	@Override
	protected void setParameters(PreparedStatement preparedStatement, List<Object> parameters) {
		try {
			if (!UtilObject.isNull(parameters) && !UtilObject.isNull(preparedStatement)) {
				for (int index = 0; index < parameters.size(); index++) {
					preparedStatement.setObject(index + 1, parameters.get(index));
				}
			}
		} catch (SQLException exception) {
			var userMessage = TipoEventoSqlServerDAOMessages.SET_PARAMETERS_SQL_EXCEPTION_USER_MESSAGE;
			var technicalMessage = TipoEventoSqlServerDAOMessages.SET_PARAMETERS_SQL_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);
		} catch (Exception exception) {
			var userMessage = TipoEventoSqlServerDAOMessages.SET_PARAMETERS_EXCEPTION_USER_MESSAGE;
			var technicalMessage = TipoEventoSqlServerDAOMessages.SET_PARAMETERS_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);

		}

	}

	@Override
	protected List<TipoEventoEntity> executeQuery(PreparedStatement preparedStatement) {
		final List<TipoEventoEntity> result = new ArrayList<>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				var entityTmp = new TipoEventoEntity(resultSet.getObject("identificador", UUID.class),
						resultSet.getString("nombre"), resultSet.getString("descripcion"));

				result.add(entityTmp);
			}

		} catch (SQLException exception) {
			var userMessage = TipoEventoSqlServerDAOMessages.EXECUTE_QUERY_SQL_EXCEPTION_USER_MESSAGE;
			var technicalMessage = TipoEventoSqlServerDAOMessages.EXECUTE_QUERY_SQL_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);
		} catch (Exception exception) {
			var userMessage = TipoEventoSqlServerDAOMessages.EXECUTE_QUERY_EXCEPTION_USER_MESSAGE;
			var technicalMessage = TipoEventoSqlServerDAOMessages.EXECUTE_QUERY_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);

		}
		return result;
	}

}
