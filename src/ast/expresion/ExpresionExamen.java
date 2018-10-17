package ast.expresion;

import visitor.Visitor;

public class ExpresionExamen extends ExpresionBinaria {

	public ExpresionExamen(int linea, int columna, Expresion expLeft, String operador, Expresion expRight) {
		super(linea, columna, expLeft, operador, expRight);
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}