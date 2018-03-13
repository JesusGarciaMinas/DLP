package ast.tipo;

import ast.nodo.AbstractNodoAST;
import visitor.Visitor;

public class TipoVoid extends AbstractNodoAST implements Tipo {

	public TipoVoid(int linea, int columna) {
		super(linea, columna);
	}

	@Override
	public String toString() {
		return "TipoVoid";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}