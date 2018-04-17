package ast.tipo;

import visitor.Visitor;

public class TipoArray extends AbstractTipo {

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
		return "[" + tama�o + ", " + tipo + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public Tipo corchetes(Tipo tipo) {
		if (tipo instanceof TipoEntero || tipo instanceof TipoChar)
			return this.tipo;
		return null;
	}

	@Override
	public int numeroDeBytes() {
		return tama�o * tipo.numeroDeBytes();
	}
}