package ast.expresion;

import visitor.Visitor;

public class LogicaUnaria extends AbstractExpresion implements Expresion {
	
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
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}