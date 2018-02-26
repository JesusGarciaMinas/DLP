package ast.sentencia;

import java.util.ArrayList;
import java.util.List;

import ast.expresion.Expresion;
import ast.nodo.AbstractNodoAST;

public class Lectura extends AbstractNodoAST implements Sentencia {

	private List<Expresion> expresiones;

	public Lectura(int linea, int columna, List<Expresion> expresiones) {
		super(linea, columna);
		setExpresiones(expresiones);
	}

	public List<Expresion> getExpresiones() {
		return expresiones;
	}

	private void setExpresiones(List<Expresion> expresiones) {
		this.expresiones = new ArrayList<Expresion>(expresiones);
	}

	@Override
	public String toString() {
		return "Lectura [expresiones=" + expresiones + "]";
	}
}