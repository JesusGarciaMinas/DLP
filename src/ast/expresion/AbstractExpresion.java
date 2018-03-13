package ast.expresion;

import ast.nodo.AbstractNodoAST;
import ast.tipo.Tipo;

public abstract class AbstractExpresion extends AbstractNodoAST implements Expresion {

	private boolean lValue;
	private Tipo tipoExpresion;

	public AbstractExpresion(int linea, int columna) {
		super(linea, columna);
	}

	@Override
	public boolean islValue() {
		return lValue;
	}

	@Override
	public void setlValue(boolean lValue) {
		this.lValue = lValue;
	}

	@Override
	public Tipo getTipoExpresion() {
		return tipoExpresion;
	}

	@Override
	public void setTipoExpresion(Tipo tipoExpresion) {
		this.tipoExpresion = tipoExpresion;
	}
}