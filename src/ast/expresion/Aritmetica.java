package ast.expresion;

import visitor.Visitor;

public class Aritmetica extends ExpresionBinaria {

	public Aritmetica(int linea, int columna, Expresion operador1, String operando, Expresion operador2) {
		super(linea, columna, operador1, operando, operador2);
	}

	@Override
	public String toString() {
		return "Aritmetica [" + super.toString() + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}