package ast.sentencia;

import ast.expresion.Expresion;
import ast.nodo.AbstractNodoAST;
import visitor.Visitor;

public class Asignacion extends AbstractNodoAST implements Sentencia {

	private Expresion expLeft, expRight;

	public Asignacion(int linea, int columna, Expresion expLeft, Expresion expRight) {
		super(linea, columna);
		setExpLeft(expLeft);
		setExpRight(expRight);
	}

	public Expresion getExpLeft() {
		return expLeft;
	}

	private void setExpLeft(Expresion expLeft) {
		this.expLeft = expLeft;
	}

	public Expresion getExpRight() {
		return expRight;
	}

	private void setExpRight(Expresion expRight) {
		this.expRight = expRight;
	}

	@Override
	public String toString() {
		return "Asignacion [expLeft=" + expLeft + ", expRight=" + expRight + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}