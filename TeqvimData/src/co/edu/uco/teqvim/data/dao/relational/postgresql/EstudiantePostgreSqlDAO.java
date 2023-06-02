package co.edu.uco.teqvim.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.teqvim.crosscutting.exception.TeqvimDataException;
import co.edu.uco.teqvim.crosscutting.exception.TeqvimException;
import co.edu.uco.teqvim.crosscutting.utils.Messages.EstudianteSqlServerDAOMessages;
import co.edu.uco.teqvim.crosscutting.utils.UtilObject;
import co.edu.uco.teqvim.crosscutting.utils.UtilText;
import co.edu.uco.teqvim.crosscutting.utils.UtilUUID;
import co.edu.uco.teqvim.data.dao.EstudianteDAO;
import co.edu.uco.teqvim.data.dao.relational.SqlDAO;
import co.edu.uco.teqvim.entities.EstadoEstudianteEntity;
import co.edu.uco.teqvim.entities.EstudianteEntity;
import co.edu.uco.teqvim.entities.PaisEntity;
import co.edu.uco.teqvim.entities.RespuestaEntity;
import co.edu.uco.teqvim.entities.TipoDocumentoEntity;

public final class EstudiantePostgreSqlDAO extends SqlDAO<EstudianteEntity> implements EstudianteDAO {

	public EstudiantePostgreSqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public void create(EstudianteEntity entity) {
		var sqlStatement = "INSERT INTO Estudiante(identificador, primerNombre, segundoNombre, primerApellido, segundoApellido, numeroTelefonico, correo, contraseña, fechaNacimiento, tipoDocumento, numeroDocumento, confirmacionCorreo, pais, estadoEstudiante) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try (var PreparedStatement = getConnection().prepareStatement(sqlStatement)) {

			PreparedStatement.setObject(1, entity.getIdentificador());
			PreparedStatement.setString(2, entity.getPrimerNombre());
			PreparedStatement.setString(3, entity.getSegundoNombre());
			PreparedStatement.setObject(4, entity.getPrimerApellido());
			PreparedStatement.setObject(5, entity.getSegudoApellido());
			PreparedStatement.setString(6, entity.getNumeroTelefonico());
			PreparedStatement.setString(7, entity.getCorreo());
			PreparedStatement.setObject(8, entity.getContrasena());
			PreparedStatement.setObject(9, entity.getContrasena());
			PreparedStatement.setObject(10, entity.getTipoDocumento());
			PreparedStatement.setString(11, entity.getNumeroDocumento());
			PreparedStatement.setObject(12, entity.getConfirmacionCorreo());
			PreparedStatement.setObject(13, entity.getPais());
			PreparedStatement.setObject(14, entity.getEstado());

			PreparedStatement.executeUpdate();

		} catch (SQLException exception) {
			var userMessage = EstudianteSqlServerDAOMessages.CREATE_SQL_EXCEPTION_USER_MESSAGE;
			var technicalMessage = EstudianteSqlServerDAOMessages.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);
		} catch (Exception exception) {
			var userMessage = EstudianteSqlServerDAOMessages.CREATE_EXCEPTION_USER_MESSAGE;
			var technicalMessage = EstudianteSqlServerDAOMessages.CREATE_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);

		}

	}

	@Override
	public List<EstudianteEntity> read(EstudianteEntity entity) {
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
			var userMessage = EstudianteSqlServerDAOMessages.READ_SQL_EXCEPTION_USER_MESSAGE;
			var technicalMessage = EstudianteSqlServerDAOMessages.READ_SQL_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);
		} catch (Exception exception) {
			var userMessage = EstudianteSqlServerDAOMessages.READ_EXCEPTION_USER_MESSAGE;
			var technicalMessage = EstudianteSqlServerDAOMessages.READ_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);

		}
	}

	@Override
	public void update(EstudianteEntity entity) {
		var sqlStatement = "UPDATE Estudiante SET primerNombre=?, segundoNombre=?, primerApellido=?, segundoApellido=?, numeroTelefonico=?, correo=?, contraseña=?, fechaNacimiento=?, tipoDocumento=?, numeroDocumento=?, confirmacionCorreo=?, pais=?, estadoEstudiante=? WHERE identificador=? ";

		try (var PreparedStatement = getConnection().prepareStatement(sqlStatement)) {

			PreparedStatement.setString(1, entity.getPrimerNombre());
			PreparedStatement.setString(2, entity.getSegundoNombre());
			PreparedStatement.setObject(3, entity.getPrimerApellido());
			PreparedStatement.setObject(4, entity.getSegudoApellido());
			PreparedStatement.setString(5, entity.getNumeroTelefonico());
			PreparedStatement.setString(6, entity.getCorreo());
			PreparedStatement.setObject(7, entity.getContrasena());
			PreparedStatement.setObject(8, entity.getFechaNacimiento());
			PreparedStatement.setObject(9, entity.getTipoDocumento());
			PreparedStatement.setString(10, entity.getNumeroDocumento());
			PreparedStatement.setObject(11, entity.getConfirmacionCorreo());
			PreparedStatement.setObject(12, entity.getPais());
			PreparedStatement.setObject(13, entity.getEstado());

			PreparedStatement.executeUpdate();

		} catch (SQLException exception) {
			var userMessage = EstudianteSqlServerDAOMessages.UPDATE_SQL_EXCEPTION_USER_MESSAGE;
			var technicalMessage = EstudianteSqlServerDAOMessages.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);
		} catch (Exception exception) {
			var userMessage = EstudianteSqlServerDAOMessages.UPDATE_EXCEPTION_USER_MESSAGE;
			var technicalMessage = EstudianteSqlServerDAOMessages.UPDATE_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);

		}

	}

	@Override
	public void delete(EstudianteEntity entity) {
		var sqlStatement = "DELETE FROM Estudiante WHERE identificador=? ";

		try (var PreparedStatement = getConnection().prepareStatement(sqlStatement)) {

			PreparedStatement.setObject(1, entity.getIdentificador());

			PreparedStatement.executeUpdate();

		} catch (SQLException exception) {
			var userMessage = EstudianteSqlServerDAOMessages.DELETE_SQL_EXCEPTION_USER_MESSAGE;
			var technicalMessage = EstudianteSqlServerDAOMessages.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);
		} catch (Exception exception) {
			var userMessage = EstudianteSqlServerDAOMessages.DELETE_EXCEPTION_USER_MESSAGE;
			var technicalMessage = EstudianteSqlServerDAOMessages.DELETE_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);

		}
	}

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, descripcion ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM Estudiante ";
	}

	@Override
	protected String prepareWhere(EstudianteEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;

		if (!UtilObject.isNull(entity)) {

			if (!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}

			if (!UtilText.getUtilText().isEmpty(entity.getPrimerNombre())) {
				parameters.add(entity.getPrimerNombre());
				where.append(setWhere ? "WHERE " : "AND ").append("primerNombre=? ");
				setWhere = false;
			}

			if (!UtilText.getUtilText().isEmpty(entity.getSegundoNombre())) {
				parameters.add(entity.getSegundoNombre());
				where.append(setWhere ? "WHERE " : "AND ").append("segundoNombre=? ");
			}

			if (!UtilText.getUtilText().isEmpty(entity.getPrimerApellido())) {
				parameters.add(entity.getPrimerApellido());
				where.append(setWhere ? "WHERE " : "AND ").append("primerApellido=? ");
				setWhere = false;
			}

			if (!UtilText.getUtilText().isEmpty(entity.getSegudoApellido())) {
				parameters.add(entity.getSegudoApellido());
				where.append(setWhere ? "WHERE " : "AND ").append("segundoApellido=? ");
			}

			if (!UtilText.getUtilText().isEmpty(entity.getNumeroTelefonico())) {
				parameters.add(entity.getNumeroTelefonico());
				where.append(setWhere ? "WHERE " : "AND ").append("numeroTelefonico=? ");
				setWhere = false;
			}

			if (!UtilText.getUtilText().isEmpty(entity.getCorreo())) {
				parameters.add(entity.getCorreo());
				where.append(setWhere ? "WHERE " : "AND ").append("correo=? ");
			}

			if (!UtilText.getUtilText().isEmpty(entity.getContrasena())) {
				parameters.add(entity.getContrasena());
				where.append(setWhere ? "WHERE " : "AND ").append("contrasena=? ");
				setWhere = false;
			}

			if (!UtilObject.isNull(entity.getTipoDocumento())) {
				parameters.add(entity.getTipoDocumento());
				where.append(setWhere ? "WHERE " : "AND ").append("tipoDocumento=? ");
				setWhere = false;
			}

			if (!UtilObject.isNull(entity.getFechaNacimiento())) {
				parameters.add(entity.getFechaNacimiento());
				where.append(setWhere ? "WHERE " : "AND ").append("fechaNacimineto=? ");
				setWhere = false;
			}

			if (!UtilText.getUtilText().isEmpty(entity.getNumeroDocumento())) {
				parameters.add(entity.getNumeroDocumento());
				where.append(setWhere ? "WHERE " : "AND ").append("numeroDocumento=? ");
			}

			if (!UtilObject.isNull(entity.getConfirmacionCorreo())) {
				parameters.add(entity.getConfirmacionCorreo());
				where.append(setWhere ? "WHERE " : "AND ").append("confirmacionCorreo=? ");
				setWhere = false;
			}

			if (!UtilObject.isNull(entity.getPais())) {
				parameters.add(entity.getPais());
				where.append(setWhere ? "WHERE " : "AND ").append("pais=? ");
			}

			if (!UtilObject.isNull(entity.getEstado())) {
				parameters.add(entity.getEstado());
				where.append(setWhere ? "WHERE " : "AND ").append("estadoEstudiante=? ");
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
			var userMessage = EstudianteSqlServerDAOMessages.SET_PARAMETERS_SQL_EXCEPTION_USER_MESSAGE;
			var technicalMessage = EstudianteSqlServerDAOMessages.SET_PARAMETERS_SQL_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);
		} catch (Exception exception) {
			var userMessage = EstudianteSqlServerDAOMessages.SET_PARAMETERS_EXCEPTION_USER_MESSAGE;
			var technicalMessage = EstudianteSqlServerDAOMessages.SET_PARAMETERS_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);

		}

	}

	@Override
	protected List<EstudianteEntity> executeQuery(PreparedStatement preparedStatement) {
		final List<EstudianteEntity> result = new ArrayList<>();

		try (var resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				var entityTmp = new EstudianteEntity(resultSet.getObject("identificador", UUID.class),
						resultSet.getString("primerNombre"), resultSet.getString("segundoNombre"),
						resultSet.getString("primerApellido"), resultSet.getString("segundoApellido"),
						resultSet.getString("numeroTelefonico"), resultSet.getString("correo"),
						resultSet.getString("contrasena"), resultSet.getObject("fechaNacimineto", LocalDate.class),
						resultSet.getObject("tipoDocumento", TipoDocumentoEntity.class),
						resultSet.getString("numeroDocumento"),
						resultSet.getObject("confirmacionCorreo", RespuestaEntity.class),
						resultSet.getObject("pais", PaisEntity.class),
						resultSet.getObject("estadoEstudiante", EstadoEstudianteEntity.class));

				result.add(entityTmp);
			}

		} catch (SQLException exception) {
			var userMessage = EstudianteSqlServerDAOMessages.EXECUTE_QUERY_SQL_EXCEPTION_USER_MESSAGE;
			var technicalMessage = EstudianteSqlServerDAOMessages.EXECUTE_QUERY_SQL_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);
		} catch (Exception exception) {
			var userMessage = EstudianteSqlServerDAOMessages.EXECUTE_QUERY_EXCEPTION_USER_MESSAGE;
			var technicalMessage = EstudianteSqlServerDAOMessages.EXECUTE_QUERY_EXCEPTION_TECHNICAL_MESSAGE;

			throw TeqvimDataException.create(technicalMessage, userMessage, exception);

		}
		return result;
	}

}
