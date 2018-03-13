package ast.sentencia;

import java.util.ArrayList;
import java.util.List;

import ast.expresion.Expresion;
import ast.nodo.AbstractNodoAST;
import visitor.Visitor;

public class SentWhile extends AbstractNodoAST implements Sentencia {

	private Expresion condicion;
	private List<Sentencia> sentWhile;

	public SentWhile(int linea, int columna, Expresion condicion, List<Sentencia> sentWhile) {
		super(linea, columna);
		setCondicion(condicion);
		setSentWhile(sentWhile);
	}

	public Expresion getCondicion() {
		return condicion;
	}

	private void setCondicion(Expresion condicion) {
		this.condicion = condicion;
	}

	public List<Sentencia> getSentWhile() {
		return sentWhile;
	}

	private void setSentWhile(List<Sentencia> sentWhile) {
		this.sentWhile = new ArrayList<Sentencia>(sentWhile);
	}

	@Override
	public String toString() {
		return "SentWhile [condicion=" + condicion + ", sentWhile=" + sentWhile + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}