package ast.tipo;

import ast.nodo.AbstractNodoAST;

public class TipoChar extends AbstractNodoAST implements Tipo {

	public TipoChar(int linea, int columna) {
		super(linea, columna);
	}

	@Override
	public String toString() {
		return "TipoChar";
	}
}