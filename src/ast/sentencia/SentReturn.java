package ast.sentencia;

import ast.expresion.Expresion;
import ast.nodo.AbstractNodoAST;

public class SentReturn extends AbstractNodoAST implements Sentencia {

	private Expresion expresion;

	public SentReturn(int linea, int columna, Expresion expresion) {
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
		return "SentReturn [expresion=" + expresion + "]";
	}
}