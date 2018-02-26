package ast.expresion;

import ast.nodo.AbstractNodoAST;

public class LiteralChar extends AbstractNodoAST implements Expresion {

	private char valor;

	public LiteralChar(int linea, int columna, char valor) {
		super(linea, columna);
		setValor(valor);
	}

	public char getValor() {
		return valor;
	}

	public void setValor(char valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralChar [valor=" + valor + "]";
	}
}