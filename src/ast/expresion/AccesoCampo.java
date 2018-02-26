package ast.expresion;

import ast.nodo.AbstractNodoAST;

public class AccesoCampo extends AbstractNodoAST implements Expresion {

	private Expresion expresion;
	private String nombre;

	public AccesoCampo(int linea, int columna, Expresion expresion, String Nombre) {
		super(linea, columna);
		setExpresion(expresion);
		setNombre(nombre);
	}

	public Expresion getExpresion() {
		return expresion;
	}

	private void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "AccesoCampo [expresion=" + expresion + ", nombre=" + nombre + "]";
	}
}