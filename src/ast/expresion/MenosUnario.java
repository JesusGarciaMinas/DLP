package ast.expresion;

import visitor.Visitor;

public class MenosUnario extends AbstractExpresion implements Expresion {

	private Expresion operando;

	public MenosUnario(int linea, int columna, Expresion operando) {
		super(linea, columna);
		setOperando(operando);
	}

	private void setOperando(Expresion operando) {
		this.operando = operando;
	}

	public Expresion getOperando() {
		return operando;
	}

	@Override
	public String toString() {
		return "MenosUnario [operando=" + operando + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}