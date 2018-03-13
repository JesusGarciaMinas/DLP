package ast.expresion;

import visitor.Visitor;

public class AccesoArray extends AbstractExpresion implements Expresion {

	private Expresion nombre, tamaño;

	public AccesoArray(int linea, int columna, Expresion nombre, Expresion tamaño) {
		super(linea, columna);
		setNombre(nombre);
		setTamaño(tamaño);
	}

	public Expresion getNombre() {
		return nombre;
	}

	private void setNombre(Expresion nombre) {
		this.nombre = nombre;
	}

	public Expresion getTamaño() {
		return tamaño;
	}

	private void setTamaño(Expresion tamaño) {
		this.tamaño = tamaño;
	}

	@Override
	public String toString() {
		return "AccesoArray [nombre=" + nombre + ", tamaño=" + tamaño + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}