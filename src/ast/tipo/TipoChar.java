package ast.tipo;

import ast.nodo.AbstractNodoAST;
import visitor.Visitor;

public class TipoChar extends AbstractNodoAST implements Tipo {

	public TipoChar(int linea, int columna) {
		super(linea, columna);
	}

	@Override
	public String toString() {
		return "TipoChar";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}