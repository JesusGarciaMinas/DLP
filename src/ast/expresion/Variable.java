package ast.expresion;

import ast.nodo.AbstractNodoAST;

public class Variable extends AbstractNodoAST implements Expresion {

	private String nombre;

	public Variable(int linea, int columna, String nombre) {
		super(linea, columna);
		setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Variable [nombre=" + nombre + "]";
	}
}