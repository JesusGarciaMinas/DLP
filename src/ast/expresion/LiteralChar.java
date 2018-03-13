package ast.expresion;

import visitor.Visitor;

public class LiteralChar extends AbstractExpresion implements Expresion {

	private String valor;

	public LiteralChar(int linea, int columna, String valor) {
		super(linea, columna);
		setValor(valor);
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralChar [valor=" + valor + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}