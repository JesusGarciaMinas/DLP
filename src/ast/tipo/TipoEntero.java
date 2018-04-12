package ast.tipo;

import visitor.Visitor;

public class TipoEntero extends AbstractTipo {

	private static TipoEntero instancia = new TipoEntero(0, 0);

	private TipoEntero(int linea, int columna) {
		super(linea, columna);
	}

	@Override
	public String toString() {
		return "int";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	public static TipoEntero getInstancia() {
		return instancia;
	}

	@Override
	public Tipo aritmetica(Tipo tipo) {
		if (tipo instanceof TipoEntero || tipo instanceof TipoError || tipo instanceof TipoDecimal)
			return tipo;
		else if (tipo instanceof TipoChar)
			return this;
		return null;
	}

	@Override
	public Tipo logica(Tipo tipo) {
		if (tipo instanceof TipoChar || tipo instanceof TipoEntero)
			return this;
		else if (tipo instanceof TipoError)
			return tipo;
		return null;
	}

	public Tipo comparacion(Tipo tipo) {
		if (tipo.esBasico())
			return this;
		else if (tipo instanceof TipoError)
			return tipo;
		return null;
	}

	@Override
	public Tipo promocionaA(Tipo tipo) {
		if (tipo.getClass() == TipoEntero.class || tipo.getClass() == TipoDecimal.class
				|| tipo.getClass() == TipoError.class)
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
	public Tipo aritmetica() {
		return this;
	}

	@Override
	public boolean esBasico() {
		return true;
	}

	@Override
	public boolean esLogico() {
		return true;
	}

	@Override
	public int numeroDeBytes() {
		return 2;
	}

	@Override
	public char suffix() {
		return 'i';
	}

	@Override
	public Tipo superType(Tipo tipo) {
		if (tipo instanceof TipoDecimal)
			return tipo;
		return this;
	}
}