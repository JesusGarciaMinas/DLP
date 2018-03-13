package ast.expresion;

import visitor.Visitor;

public class Comparacion extends ExpresionBinaria {

	public Comparacion(int linea, int columna, Expresion expLeft, String operador, Expresion expRight) {
		super(linea, columna, expLeft, operador, expRight);
	}

	@Override
	public String toString() {
		return "Comparacion [=" + super.toString() + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}