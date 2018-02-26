package ast.expresion;

import ast.nodo.AbstractNodoAST;

public class LogicaUnaria extends AbstractNodoAST implements Expresion {
	
	private Expresion expresion;

	public LogicaUnaria(int linea, int columna, Expresion expresion) {
		super(linea, columna);
		setExpresion(expresion);
	}

	public Expresion getExpresion() {
		return expresion;
	}

	private void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "LogicaUnaria [expresion=" + expresion + "]";
	}
}