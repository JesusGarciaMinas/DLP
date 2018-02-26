package ast.sentencia;

import java.util.ArrayList;
import java.util.List;

import ast.expresion.Expresion;
import ast.expresion.Variable;
import ast.nodo.AbstractNodoAST;

public class InvocacionFuncion extends AbstractNodoAST implements Expresion, Sentencia {

	private Variable nombre;
	private List<Expresion> argumentos;

	public InvocacionFuncion(int linea, int columna, Variable nombre, List<Expresion> argumentos) {
		super(linea, columna);
		setNombre(nombre);
		setArgumentos(argumentos);
	}

	public Variable getNombre() {
		return nombre;
	}

	private void setNombre(Variable nombre) {
		this.nombre = nombre;
	}

	public List<Expresion> getArgumentos() {
		return argumentos;
	}

	private void setArgumentos(List<Expresion> argumentos) {
		this.argumentos = new ArrayList<Expresion>(argumentos);
	}

	@Override
	public String toString() {
		return "InvocacionFuncion [nombre=" + nombre + ", argumentos=" + argumentos + "]";
	}
}