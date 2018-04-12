package ast.expresion;

import ast.definicion.Definicion;
import visitor.Visitor;

public class Variable extends AbstractExpresion {

	private String nombre;
	private Definicion definicion;

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

	public void setDefinicion(Definicion definicion) {
		this.definicion = definicion;
	}

	public Definicion getDefinicion() {
		return definicion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variable other = (Variable) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}