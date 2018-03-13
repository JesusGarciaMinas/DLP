package ast.expresion;

import visitor.Visitor;

public class AccesoArray extends AbstractExpresion implements Expresion {

	private Expresion nombre, tama�o;

	public AccesoArray(int linea, int columna, Expresion nombre, Expresion tama�o) {
		super(linea, columna);
		setNombre(nombre);
		setTama�o(tama�o);
	}

	public Expresion getNombre() {
		return nombre;
	}

	private void setNombre(Expresion nombre) {
		this.nombre = nombre;
	}

	public Expresion getTama�o() {
		return tama�o;
	}

	private void setTama�o(Expresion tama�o) {
		this.tama�o = tama�o;
	}

	@Override
	public String toString() {
		return "AccesoArray [nombre=" + nombre + ", tama�o=" + tama�o + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}