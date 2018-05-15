package ast.expresion;

import visitor.Visitor;

public class LiteralDecimal extends AbstractExpresion implements Expresion {

	private float valor;

	public LiteralDecimal(int linea, int columna, float valor) {
		super(linea, columna);
		setValor(valor);
	}

	public float getValor() {
		return valor;
	}

	private void setValor(float valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "LiteralDecimal [valor=" + valor + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}