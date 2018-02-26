package ast.definicion;

import ast.nodo.AbstractNodoAST;
import ast.tipo.Tipo;

public class DefVariable extends AbstractNodoAST implements Definicion {

	private String nombre;
	private Tipo tipo;

	public DefVariable(int linea, int columna, String nombre, Tipo tipo) {
		super(linea, columna);
		setNombre(nombre);
		setTipo(tipo);
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	private void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "DefVariable [nombre=" + nombre + ", tipo=" + tipo + "]";
	}
}