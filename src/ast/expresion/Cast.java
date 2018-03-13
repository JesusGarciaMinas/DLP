package ast.expresion;

import ast.tipo.Tipo;
import visitor.Visitor;

public class Cast extends AbstractExpresion implements Expresion {

	private Tipo tipo;
	private Expresion expresion;

	public Cast(int linea, int columna, Tipo tipo, Expresion expresion) {
		super(linea, columna);
		setTipo(tipo);
		setExpresion(expresion);
	}

	public Tipo getTipo() {
		return tipo;
	}

	private void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Expresion getExpresion() {
		return expresion;
	}

	private void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	@Override
	public String toString() {
		return "Cast [tipo=" + tipo + ", expresion=" + expresion + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}