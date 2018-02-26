package ast.tipo;

import ast.nodo.AbstractNodoAST;

public class TipoEntero extends AbstractNodoAST implements Tipo {

	public TipoEntero(int linea, int columna) {
		super(linea, columna);
	}

	@Override
	public String toString() {
		return "TipoEntero";
	}
}