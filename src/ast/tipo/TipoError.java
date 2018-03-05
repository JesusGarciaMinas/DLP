package ast.tipo;

import ast.nodo.AbstractNodoAST;
import manejadorerrores.ME;

public class TipoError extends AbstractNodoAST implements Tipo {
	
	private String mensaje;

	public TipoError(int linea, int columna, String mensaje) {
		super(linea, columna);
		setMensaje(mensaje);
		ME.getME().a�adirErrores(this);
	}

	public String getMensaje() {
		return mensaje;
	}

	private void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "TipoError [mensaje=" + mensaje + ", getLinea()=" + getLinea() + ", getColumna()=" + getColumna() + "]";
	}
}