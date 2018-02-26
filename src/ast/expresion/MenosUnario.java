package ast.expresion;

import ast.nodo.AbstractNodoAST;

public class MenosUnario extends AbstractNodoAST implements Expresion {

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
}