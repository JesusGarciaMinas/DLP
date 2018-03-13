package ast.nodo;

import visitor.Visitor;

public interface NodoAST {

	public int getLinea();

	public int getColumna();

	public Object accept(Visitor visitor, Object param);
}