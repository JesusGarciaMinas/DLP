package ast.tipo;

import java.util.List;

import ast.nodo.AbstractNodoAST;

public abstract class AbstractTipo extends AbstractNodoAST implements Tipo {

	public AbstractTipo(int linea, int columna) {
		super(linea, columna);
	}

	@Override
	public boolean esLogico() {
		return false;
	}

	@Override
	public boolean esBasico() {
		return false;
	}

	@Override
	public Tipo aritmetica(Tipo tipo) {
		return null;
	}

	@Override
	public Tipo aritmetica() {
		return null;
	}

	public Tipo comparacion(Tipo tipo) {
		return null;
	}

	public Tipo logica(Tipo tipo) {
		return null;
	}

	public Tipo logica() {
		return null;
	}

	public Tipo promocionaA(Tipo tipo) {
		return null;
	}

	public Tipo parentesis(List<Tipo> tipos) {
		return null;
	}

	@Override
	public Tipo punto(String nombreCampo) {
		return null;
	}

	@Override
	public Tipo corchetes(Tipo tipo) {
		return null;
	}

	@Override
	public Tipo cast(Tipo tipo) {
		return null;
	}

	@Override
	public int numeroDeBytes() {
		return 0;
	}

	@Override
	public char suffix() {
		return '0';
	}
	
	@Override
	public Tipo superType (Tipo tipo) {
		return null;
	}
	
	@Override
	public Campo get (String nombre) {
		return null;
	}
}