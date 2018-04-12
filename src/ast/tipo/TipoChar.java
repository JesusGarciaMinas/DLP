package ast.tipo;

import visitor.Visitor;

public class TipoChar extends AbstractTipo {

	private static TipoChar instancia = new TipoChar(0, 0);

	private TipoChar(int linea, int columna) {
		super(linea, columna);
	}

	public static TipoChar getInstancia() {
		return instancia;
	}

	@Override
	public String toString() {
		return "char";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
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
	public Tipo aritmetica(Tipo tipo) {
		if (tipo instanceof TipoEntero || tipo instanceof TipoError || tipo instanceof TipoDecimal)
			return tipo;
		else if (tipo instanceof TipoChar)
			return TipoEntero.getInstancia();
		return null;
	}

	@Override
	public Tipo aritmetica() {
		return TipoEntero.getInstancia();
	}

	public Tipo comparacion(Tipo tipo) {
		if (tipo.esBasico())
			return TipoEntero.getInstancia();
		else if (tipo instanceof TipoError)
			return tipo;
		return null;
	}

	@Override
	public Tipo promocionaA(Tipo tipo) {
		if (tipo.getClass() == TipoEntero.class || tipo.getClass() == TipoDecimal.class
				|| tipo.getClass() == TipoError.class || tipo.getClass() == TipoChar.class)
			return tipo;
		return null;
	}

	@Override
	public Tipo logica(Tipo tipo) {
		if (tipo instanceof TipoChar || tipo instanceof TipoEntero)
			return TipoEntero.getInstancia();
		return null;
	}

	@Override
	public Tipo cast(Tipo tipo) {
		if (tipo.esBasico() || tipo instanceof TipoError)
			return tipo;
		return null;
	}

	@Override
	public int numeroDeBytes() {
		return 1;
	}

	@Override
	public char suffix() {
		return 'b';
	}

	@Override
	public Tipo superType(Tipo tipo) {
		return tipo;
	}
}