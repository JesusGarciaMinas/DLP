package visitor;

import ast.*;
import ast.definicion.*;
import ast.expresion.*;
import ast.sentencia.*;
import ast.tipo.*;

public interface Visitor {
	//Expresiones
		//Constantes
	public Object visit (LiteralEntero e, Object param);
	public Object visit (LiteralDecimal r, Object param);
	public Object visit (LiteralChar c, Object param);
		
		//Expresiones binaras
	public Object visit (LogicaBinaria b, Object param);
	public Object visit (Comparacion c, Object param);
	public Object visit (Aritmetica a, Object param);
	public Object visit (AccesoArray a, Object param);
	public Object visit (AccesoCampo c, Object param);
	
		//Expresiones unarias
	public Object visit (LogicaUnaria u, Object param);
	public Object visit (MenosUnario u, Object param);
	
		//Otras expresiones
	public Object visit (Cast c, Object param);
	public Object visit (Variable v, Object param);
	
	//Expresion + Sentencia
	public Object visit (InvocacionFuncion f, Object param); //Recomendable separarlo en Invocacion (Sentencia)
	
	//Sentencias
	public Object visit (SentIf i, Object param);
	public Object visit (SentReturn r, Object param);
	public Object visit (SentWhile w, Object param);
	public Object visit (Escritura e, Object param);
	public Object visit (Lectura l, Object param);
	public Object visit (Asignacion a, Object param);
	
	//Tipos
	public Object visit (Campo c, Object param);
	public Object visit (TipoArray a, Object param);
	public Object visit (TipoChar c, Object param);
	public Object visit (TipoEntero e, Object param);
	public Object visit (TipoError e, Object param);
	public Object visit (TipoDecimal f, Object param);
	public Object visit (TipoFuncion f, Object param);
	public Object visit (TipoStruct s, Object param);
	public Object visit (TipoVoid v, Object param);
	
	//Definiciones
	public Object visit (DefFuncion f, Object param);
	public Object visit (DefVariable v, Object param);
	
	//Programa
	public Object visit (Programa p, Object param);
}