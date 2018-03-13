package ast.expresion;

import visitor.Visitor;

public class AccesoCampo extends AbstractExpresion implements Expresion {

	private Expresion expresion;
	private String nombre;

	public AccesoCampo(int linea, int columna, Expresion expresion, String nombre) {
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
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}