package ast.expresion;

import ast.nodo.AbstractNodoAST;

public class LiteralEntero extends AbstractNodoAST implements Expresion {

	private int valor;

	public LiteralEntero(int linea, int columna, int valor) {
		super(linea, columna);
		setValor(valor);
	}

	public int getValor() {
		return valor;
	}

	private void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralEntero [valor=" + valor + "]";
	}
}