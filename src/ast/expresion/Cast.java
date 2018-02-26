package ast.expresion;

import ast.nodo.AbstractNodoAST;
import ast.tipo.Tipo;

public class Cast extends AbstractNodoAST implements Expresion {

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
}