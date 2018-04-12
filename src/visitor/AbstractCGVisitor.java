package visitor;

import ast.Programa;
import ast.definicion.DefFuncion;
import ast.definicion.DefVariable;
import ast.expresion.AccesoArray;
import ast.expresion.AccesoCampo;
import ast.expresion.Aritmetica;
import ast.expresion.Cast;
import ast.expresion.Comparacion;
import ast.expresion.InvocacionFuncion;
import ast.expresion.LiteralChar;
import ast.expresion.LiteralDecimal;
import ast.expresion.LiteralEntero;
import ast.expresion.LogicaBinaria;
import ast.expresion.LogicaUnaria;
import ast.expresion.MenosUnario;
import ast.expresion.Variable;
import ast.sentencia.Asignacion;
import ast.sentencia.Escritura;
import ast.sentencia.Lectura;
import ast.sentencia.SentIf;
import ast.sentencia.SentReturn;
import ast.sentencia.SentWhile;
import ast.tipo.Campo;
import ast.tipo.TipoArray;
import ast.tipo.TipoChar;
import ast.tipo.TipoDecimal;
import ast.tipo.TipoEntero;
import ast.tipo.TipoError;
import ast.tipo.TipoFuncion;
import ast.tipo.TipoStruct;
import ast.tipo.TipoVoid;

public class AbstractCGVisitor implements Visitor {

	@Override
	public Object visit(LiteralEntero e, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");
	}

	@Override
	public Object visit(LiteralDecimal r, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(LiteralChar c, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(LogicaBinaria b, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(Comparacion c, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(Aritmetica a, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(AccesoArray a, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(AccesoCampo c, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(LogicaUnaria u, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(MenosUnario u, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(Cast c, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(Variable v, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(InvocacionFuncion f, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(SentIf i, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(SentReturn r, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(SentWhile w, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(Escritura e, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(Lectura l, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(Asignacion a, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(Campo c, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(TipoArray a, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(TipoChar c, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(TipoEntero e, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(TipoError e, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(TipoDecimal f, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(TipoFuncion f, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(TipoStruct s, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(TipoVoid v, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(DefFuncion f, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(DefVariable v, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}

	@Override
	public Object visit(Programa p, Object param) {
		throw new IllegalStateException("La generación de código no se ha realizado de forma correcta");

	}
}