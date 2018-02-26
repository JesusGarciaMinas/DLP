package ast.sentencia;

import java.util.ArrayList;
import java.util.List;

import ast.expresion.Expresion;
import ast.nodo.AbstractNodoAST;

public class SentIf extends AbstractNodoAST implements Sentencia {

	private Expresion condicion;
	private List<Sentencia> sentIf, sentElse;

	public SentIf(int linea, int columna, Expresion condicion, List<Sentencia> sentIf, List<Sentencia> sentElse) {
		super(linea, columna);
		setCondicion(condicion);
		setSentIf(sentIf);
		setSentElse(sentElse);
	}

	public Expresion getCondicion() {
		return condicion;
	}

	private void setCondicion(Expresion condicion) {
		this.condicion = condicion;
	}

	public List<Sentencia> getSentIf() {
		return sentIf;
	}

	private void setSentIf(List<Sentencia> sentIf) {
		this.sentIf = new ArrayList<Sentencia>(sentIf);
	}

	public List<Sentencia> getSentElse() {
		return sentElse;
	}

	private void setSentElse(List<Sentencia> sentElse) {
		this.sentElse = new ArrayList<Sentencia>(sentElse);
	}

	@Override
	public String toString() {
		return "SentIf [condicion=" + condicion + ", sentIf=" + sentIf + ", sentElse=" + sentElse + "]";
	}
}