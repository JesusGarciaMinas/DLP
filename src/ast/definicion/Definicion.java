package ast.definicion;

import ast.nodo.NodoAST;
import ast.tipo.Tipo;

public interface Definicion extends NodoAST {
	public String getNombre();
	
	public void setTipo(Tipo tipo);
	
	public Tipo getTipo();
}
