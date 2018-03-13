package ast.definicion;

import ast.nodo.AbstractNodoAST;
import ast.sentencia.Sentencia;
import ast.tipo.Tipo;
import visitor.Visitor;

public class DefVariable extends AbstractNodoAST implements Definicion, Sentencia {

	private String nombre;
	private Tipo tipo;
	private int ambito;

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

	public int getAmbito() {
		return ambito;
	}

	public void setAmbito(int ambito) {
		this.ambito = ambito;
	}
	

	@Override
	public String toString() {
		return "DefVariable [nombre=" + nombre + ", tipo=" + tipo + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}