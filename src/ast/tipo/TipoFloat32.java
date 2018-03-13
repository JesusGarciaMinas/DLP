package ast.tipo;

import ast.nodo.AbstractNodoAST;
import visitor.Visitor;

public class TipoFloat32 extends AbstractNodoAST implements Tipo {

	public TipoFloat32(int linea, int columna) {
		super(linea, columna);
	}

	@Override
	public String toString() {
		return "TipoFloat32";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}