package ast.expresion;

import visitor.Visitor;

public class LogicaBinaria extends ExpresionBinaria {

	public LogicaBinaria(int linea, int columna, Expresion expLeft, String operador, Expresion expRight) {
		super(linea, columna, expLeft, operador, expRight);
	}

	@Override
	public String toString() {
		return "Logica [" + super.toString() + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}