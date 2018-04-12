package ast.tipo;

import visitor.Visitor;

public class TipoDecimal extends AbstractTipo {

	private static TipoDecimal instancia = new TipoDecimal(0, 0);

	private TipoDecimal(int linea, int columna) {
		super(linea, columna);
	}

	public static TipoDecimal getInstancia() {
		return instancia;
	}

	@Override
	public String toString() {
		return "float32";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public Tipo aritmetica() {
		return this;
	}

	@Override
	public Tipo aritmetica(Tipo tipo) {
		if (tipo.esBasico())
			return this;
		else if (tipo instanceof TipoError)
			return tipo;
		return null;
	}

	@Override
	public boolean esBasico() {
		return true;
	}

	@Override
	public Tipo comparacion(Tipo tipo) {
		if (tipo.esBasico())
			return TipoEntero.getInstancia();
		else if (tipo instanceof TipoError)
			return tipo;
		return null;
	}

	@Override
	public Tipo cast(Tipo tipo) {
		if (tipo.esBasico() || tipo instanceof TipoError)
			return tipo;
		return null;
	}

	@Override
	public Tipo promocionaA(Tipo tipo) {
		if (tipo.getClass() == TipoDecimal.class)
			return this;
		else if (tipo instanceof TipoError)
			return tipo;
		return null;
	}

	@Override
	public int numeroDeBytes() {
		return 4;
	}

	@Override
	public char suffix() {
		return 'f';
	}

	@Override
	public Tipo superType(Tipo tipo) {
		return this;
	}
}