package ast.tipo;

import ast.nodo.AbstractNodoAST;

public class TipoVoid extends AbstractNodoAST implements Tipo {

	public TipoVoid(int linea, int columna) {
		super(linea, columna);
	}

	@Override
	public String toString() {
		return "TipoVoid";
	}
}