package ast.expresion;

import ast.nodo.AbstractNodoAST;

public class LiteralDecimal extends AbstractNodoAST implements Expresion {

	private float valor;

	public LiteralDecimal(int linea, int columna, float valor) {
		super(linea, columna);
		setValor(valor);
	}

	public double getValor() {
		return valor;
	}

	private void setValor(float valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralDecimal [valor=" + valor + "]";
	}
}