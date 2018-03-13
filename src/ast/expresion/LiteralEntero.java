package ast.expresion;

import visitor.Visitor;

public class LiteralEntero extends AbstractExpresion implements Expresion {

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
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}