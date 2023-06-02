package co.edu.uco.teqvim.entities;

import java.util.UUID;

import co.edu.uco.teqvim.crosscutting.utils.UtilText;
import co.edu.uco.teqvim.crosscutting.utils.UtilUUID;

public class PaisEntity {
	
	private static final PaisEntity DEFAULT_OBJECT = new PaisEntity();
	private UUID identificador;
	private String nombre;
	
	private PaisEntity() {
		super();
		setIdentificador(UtilUUID.DEFAULT_UUID);
		setNombre(UtilText.EMPTY);
	}

	public PaisEntity(UUID identificador, String nombre) {
		super();
		setIdentificador(identificador);
		setNombre(nombre);
	}
	
	public static final PaisEntity createWithIdentificador(final UUID identificador) {
		return new PaisEntity(identificador, UtilText.EMPTY);
	}
	
	public static final PaisEntity createWithNombre(final String nombre) {
		return new PaisEntity(UtilUUID.DEFAULT_UUID, nombre);
	}

	public static PaisEntity getDefaultObject() {
		return DEFAULT_OBJECT;
	}

	public final UUID getIdentificador() {
		return identificador;
	}

	private final void setIdentificador(UUID identificador) {
		this.identificador = UtilUUID.getDefault(identificador);
	}

	public final String getNombre() {
		return nombre;
	}

	private final void setNombre(String nombre) {
		this.nombre = UtilText.getUtilText().applyTrim(nombre);
	}

}
