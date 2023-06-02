package co.edu.uco.teqvim.business.domain;

import java.time.LocalDate;
import java.util.UUID;

import co.edu.uco.teqvim.crosscutting.utils.UtilDate;
import co.edu.uco.teqvim.crosscutting.utils.UtilObject;
import co.edu.uco.teqvim.crosscutting.utils.UtilText;
import co.edu.uco.teqvim.crosscutting.utils.UtilUUID;

public final class EstudianteDomain {

	private static final EstudianteDomain DEFAULT_OBJECT = new EstudianteDomain();
	private UUID identificador;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segudoApellido;
	private String numeroTelefonico;
	private String correo;
	private String contrasena;
	private LocalDate fechaNacimiento;
	private TipoDocumentoDomain tipoDocumento;
	private String numeroDocumento;
	private RespuestaDomain confirmacionCorreo;
	private PaisDomain pais;
	private EstadoEstudianteDomain estado;

	private EstudianteDomain() {
		super();
		setIdentificador(UtilUUID.DEFAULT_UUID);
		setPrimerNombre(UtilText.EMPTY);
		setSegundoNombre(UtilText.EMPTY);
		setPrimerApellido(UtilText.EMPTY);
		setSegundoApellido(UtilText.EMPTY);
		setNumeroTelefonico(UtilText.EMPTY);
		setCorreo(UtilText.EMPTY);
		setContrasena(UtilText.EMPTY);
		setFechaNacimiento(UtilDate.DEFAULT_DATE);
		setTipoDocumento(TipoDocumentoDomain.getDefaultObject());
		setNumeroDocumento(UtilText.EMPTY);
		setConfirmacionCorreo(RespuestaDomain.getDefaultObject());
		setPais(PaisDomain.getDefaultObject());
		setEstado(EstadoEstudianteDomain.getDefaultObject());
	}

	public EstudianteDomain(UUID identificador, String primerNombre, String segundoNombre, String primerApellido,
			String segudoApellido, String numeroTelefonico, String correo, String contrasena, LocalDate fechaNacimiento,
			TipoDocumentoDomain tipoDocumento, String numeroDocumento, RespuestaDomain confirmacionCorreo,
			PaisDomain pais, EstadoEstudianteDomain estado) {
		super();
		setIdentificador(identificador);
		setPrimerNombre(primerNombre);
		setSegundoNombre(segundoNombre);
		setPrimerApellido(primerApellido);
		setSegundoApellido(segudoApellido);
		setNumeroTelefonico(numeroTelefonico);
		setCorreo(correo);
		setContrasena(contrasena);
		setTipoDocumento(tipoDocumento);
		setNumeroDocumento(numeroDocumento);
		setConfirmacionCorreo(confirmacionCorreo);
		setPais(pais);
		setEstado(estado);
		setFechaNacimiento(fechaNacimiento);
	}

	public static EstudianteDomain getDefaultObject() {
		return DEFAULT_OBJECT;
	}

	public final UUID getIdentificador() {
		return identificador;
	}

	private final void setIdentificador(final UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
	}

	public final String getPrimerNombre() {
		return primerNombre;
	}

	private final void setPrimerNombre(final String primerNombre) {
		this.primerNombre = UtilText.getUtilText().applyTrim(primerNombre);
	}

	public final String getSegundoNombre() {
		return segundoNombre;
	}

	private final void setSegundoNombre(final String segundoNombre) {
		this.segundoNombre = UtilText.getUtilText().applyTrim(segundoNombre);
	}

	public final String getPrimerApellido() {
		return primerApellido;
	}

	private final void setPrimerApellido(final String primerApellido) {
		this.primerApellido = UtilText.getUtilText().applyTrim(primerApellido);
	}

	public final String getSegudoApellido() {
		return segudoApellido;
	}

	private final void setSegundoApellido(final String segudoApellido) {
		this.segudoApellido = UtilText.getUtilText().applyTrim(segudoApellido);
	}

	public final String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	private final void setNumeroTelefonico(final String numeroTelefonico) {
		this.numeroTelefonico = UtilText.getUtilText().applyTrim(numeroTelefonico);
	}

	public final String getCorreo() {
		return correo;
	}

	private final void setCorreo(final String correo) {
		this.correo = UtilText.getDefaultEmail(correo);
	}

	public final String getContrasena() {
		return contrasena;
	}

	private final void setContrasena(final String contrasena) {
		this.contrasena = UtilText.getDefaultPassword(contrasena);
	}

	public final TipoDocumentoDomain getTipoDocumento() {
		return tipoDocumento;
	}

	private final void setTipoDocumento(final TipoDocumentoDomain tipoDocumento) {
		this.tipoDocumento = UtilObject.getDefault(tipoDocumento, TipoDocumentoDomain.getDefaultObject());
	}

	public final String getNumeroDocumento() {
		return numeroDocumento;
	}

	private final void setNumeroDocumento(final String numeroDocumento) {
		this.numeroDocumento = UtilText.getUtilText().applyTrim(numeroDocumento);
	}

	public final RespuestaDomain getConfirmacionCorreo() {
		return confirmacionCorreo;
	}

	private final void setConfirmacionCorreo(final RespuestaDomain confirmacionCorreo) {
		this.confirmacionCorreo = UtilObject.getDefault(confirmacionCorreo, RespuestaDomain.getDefaultObject());
	}

	public final PaisDomain getPais() {
		return pais;
	}

	private final void setPais(final PaisDomain pais) {
		this.pais = UtilObject.getDefault(pais, PaisDomain.getDefaultObject());
	}

	public final EstadoEstudianteDomain getEstado() {
		return estado;
	}

	private final void setEstado(final EstadoEstudianteDomain estado) {
		this.estado = UtilObject.getDefault(estado, EstadoEstudianteDomain.getDefaultObject());
	}

	public final LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	private final void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimiento = UtilDate.getDefault(fechaNacimiento);
	}

}
