package ast;

import java.util.List;

import ast.definicion.Definicion;
import ast.nodo.AbstractNodoAST;

public class Programa extends AbstractNodoAST {

	private List<Definicion> definiciones;

	public Programa(int linea, int columna, List<Definicion> definiciones) {
		super(linea, columna);
		setDefiniciones(definiciones);
	}

	public List<Definicion> getDefiniciones() {
		return definiciones;
	}

	public void setDefiniciones(List<Definicion> definiciones) {
		this.definiciones = definiciones;
	}

	@Override
	public String toString() {
		return "Programa [definiciones=" + definiciones + "]";
	}
}