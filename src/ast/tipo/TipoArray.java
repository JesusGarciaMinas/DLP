package ast.tipo;

import ast.nodo.AbstractNodoAST;
import visitor.Visitor;

public class TipoArray extends AbstractNodoAST implements Tipo {

	private int tama�o;
	private Tipo tipo;

	public TipoArray(int linea, int columna, int tam, Tipo tipo) {
		super(linea, columna);
		setTama�o(tam);
		setTipo(tipo);
	}

	public int getTama�o() {
		return tama�o;
	}

	private void setTama�o(int tama�o) {
		this.tama�o = tama�o;
	}

	public Tipo getTipo() {
		return tipo;
	}

	private void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TipoArray [tama�o=" + tama�o + ", tipo=" + tipo + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}