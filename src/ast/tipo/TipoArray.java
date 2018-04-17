package ast.tipo;

import visitor.Visitor;

public class TipoArray extends AbstractTipo {

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
		return "[" + tamaño + ", " + tipo + "]";
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
		return tamaño * tipo.numeroDeBytes();
	}
}