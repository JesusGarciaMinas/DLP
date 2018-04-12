package ast.tipo;

import ast.nodo.NodoAST;
import manejadorerrores.ME;
import visitor.Visitor;

public class TipoError extends AbstractTipo {

	private String mensaje;

	public TipoError(int linea, int columna, String mensaje) {
		super(linea, columna);
		setMensaje(mensaje);
		ME.getME().añadirErrores(this);
	}

	public TipoError(NodoAST nodo, String mensaje) {
		this(nodo.getLinea(), nodo.getColumna(), mensaje);
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

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public int numeroDeBytes() {
		throw new IllegalStateException("Este programa nunca debió llegar a ejecutarse");
	}
}