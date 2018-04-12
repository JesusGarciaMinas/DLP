package ast.tipo;

import visitor.Visitor;

public class TipoVoid extends AbstractTipo {

	private static TipoVoid instancia = new TipoVoid(0, 0);

	private TipoVoid(int linea, int columna) {
		super(linea, columna);
	}

	public static TipoVoid getInstancia() {
		return instancia;
	}

	@Override
	public String toString() {
		return "TipoVoid";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}