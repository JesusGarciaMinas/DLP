package ast.tipo;

import java.util.List;

import ast.nodo.NodoAST;

public interface Tipo extends NodoAST {

	public boolean esLogico();

	public Tipo aritmetica(Tipo tipo);

	public Tipo aritmetica();

	public Tipo comparacion(Tipo tipo);

	public Tipo logica(Tipo tipo);

	public Tipo logica();

	public Tipo punto(String nombreCampo);

	public Tipo corchetes(Tipo tipo);

	public Tipo cast(Tipo tipo);

	public boolean esBasico();

	public Tipo promocionaA(Tipo tipo); // Implementar con getClass()

	public Tipo parentesis(List<Tipo> tipos);
	
	public int numeroDeBytes();
	
	public char suffix();
	
	public Tipo superType(Tipo tipo);

	public Campo get(String nombre);
}