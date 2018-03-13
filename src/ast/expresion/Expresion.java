package ast.expresion;

import ast.nodo.NodoAST;
import ast.tipo.Tipo;

public interface Expresion extends NodoAST {

	public boolean islValue();

	public void setlValue(boolean lValue);

	public Tipo getTipoExpresion();

	public void setTipoExpresion(Tipo tipoExpresion);
}