package ast.tipo;

import ast.nodo.AbstractNodoAST;
import visitor.Visitor;

public class TipoArray extends AbstractNodoAST implements Tipo {

	private int tamaño;
	private Tipo tipo;

	public TipoArray(int linea, int columna, int tam, Tipo tipo) {
		super(linea, columna);
		setTamaño(tam);
		setTipo(tipo);
	}

	public int getTamaño() {
		return tamaño;
	}

	private void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

	public Tipo getTipo() {
		return tipo;
	}

	private void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tamaño=" + tamaño + ", tipo=" + tipo + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}