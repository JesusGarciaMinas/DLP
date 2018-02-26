package ast.expresion;

import ast.nodo.AbstractNodoAST;

public abstract class ExpresionBinaria extends AbstractNodoAST implements Expresion {

	private Expresion expLeft, expRight;
	private String operador;

	public ExpresionBinaria(int linea, int columna, Expresion expLeft, String operador, Expresion expRight) {
		super(linea, columna);
		setExpLeft(expLeft);
		setExpRight(expRight);
		setOperador(operador);
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

	public String getOperador() {
		return operador;
	}

	private void setOperador(String operador) {
		this.operador = operador;
	}

	@Override
	public String toString() {
		return "ExpresionBinaria [expLeft=" + expLeft + ", operador=" + operador + ", expRight=" + expRight + "]";
	}
}