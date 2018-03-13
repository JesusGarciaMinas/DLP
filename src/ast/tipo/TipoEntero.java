package ast.tipo;

import ast.nodo.AbstractNodoAST;
import visitor.Visitor;

public class TipoEntero extends AbstractNodoAST implements Tipo {

	public TipoEntero(int linea, int columna) {
		super(linea, columna);
	}

	@Override
	public String toString() {
		return "TipoEntero";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}