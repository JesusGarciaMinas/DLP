package visitor;

import ast.Programa;
import ast.definicion.DefVariable;
import ast.definicion.Definicion;
import ast.definicion.DefFuncion;
import ast.expresion.AccesoArray;
import ast.expresion.AccesoCampo;
import ast.expresion.Aritmetica;
import ast.expresion.Cast;
import ast.expresion.Comparacion;
import ast.expresion.Expresion;
import ast.expresion.LiteralChar;
import ast.expresion.LiteralDecimal;
import ast.expresion.LiteralEntero;
import ast.expresion.LogicaBinaria;
import ast.expresion.LogicaUnaria;
import ast.expresion.MenosUnario;
import ast.expresion.Variable;
import ast.sentencia.Asignacion;
import ast.sentencia.Escritura;
import ast.sentencia.InvocacionFuncion;
import ast.sentencia.Lectura;
import ast.sentencia.SentIf;
import ast.sentencia.SentReturn;
import ast.sentencia.SentWhile;
import ast.sentencia.Sentencia;
import ast.tipo.Campo;
import ast.tipo.TipoArray;
import ast.tipo.TipoChar;
import ast.tipo.TipoEntero;
import ast.tipo.TipoError;
import ast.tipo.TipoFloat32;
import ast.tipo.TipoFuncion;
import ast.tipo.TipoStruct;
import ast.tipo.TipoVoid;

public abstract class AbstractVisitor implements Visitor {

	@Override
	public Object visit(LiteralEntero e, Object param) {
		return null;
	}

	@Override
	public Object visit(LiteralDecimal r, Object param) {
		return null;
	}

	@Override
	public Object visit(LiteralChar c, Object param) {
		return null;
	}

	@Override
	public Object visit(LogicaBinaria b, Object param) {
		b.getExpLeft().accept(this, param);
		b.getExpRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Comparacion c, Object param) {
		c.getExpLeft().accept(this, param);
		c.getExpRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Aritmetica a, Object param) {
		a.getExpLeft().accept(this, param);
		a.getExpRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(AccesoArray a, Object param) {
		a.getNombre().accept(this, param);
		a.getTamaño().accept(this, param);
		return null;
	}

	@Override
	public Object visit(AccesoCampo c, Object param) {
		c.getExpresion().accept(this, param);
		return null;
	}

	@Override
	public Object visit(LogicaUnaria u, Object param) {
		u.getExpresion().accept(this, param);
		return null;
	}

	@Override
	public Object visit(MenosUnario u, Object param) {
		u.getOperando().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Cast c, Object param) {
		c.getTipo().accept(this, param);
		c.getExpresion().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Variable v, Object param) {
		return null;
	}

	@Override
	public Object visit(InvocacionFuncion f, Object param) {
		f.getNombre().accept(this, param);
		for (Expresion e: f.getArgumentos())
			e.accept(this, param);
		return null;
	}

	@Override
	public Object visit(SentIf i, Object param) {
		i.getCondicion().accept(this, param);
		for (Sentencia s: i.getSentIf())
			s.accept(this, param);
		for (Sentencia s: i.getSentElse())
			s.accept(this, param);
		return null;
	}

	@Override
	public Object visit(SentReturn r, Object param) {
		r.getExpresion().accept(this, param);
		return null;
	}

	@Override
	public Object visit(SentWhile w, Object param) {
		w.getCondicion().accept(this, param);
		for (Sentencia s: w.getSentWhile())
			s.accept(this, param);
		return null;
	}

	@Override
	public Object visit(Escritura e, Object param) {
		for (Expresion ex: e.getExpresiones())
			ex.accept(this, param);
		return null;
	}

	@Override
	public Object visit(Lectura l, Object param) {
		for (Expresion e: l.getExpresiones())
			e.accept(this, param);
		return null;
	}

	@Override
	public Object visit(Asignacion a, Object param) {
		a.getExpLeft().accept(this, param);
		a.getExpRight().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Campo c, Object param) {
		c.getTipoCampo().accept(this, param);
		return null;
	}

	@Override
	public Object visit(TipoArray a, Object param) {
		a.getTipo().accept(this, param);
		return null;
	}

	@Override
	public Object visit(TipoChar c, Object param) {
		return null;
	}

	@Override
	public Object visit(TipoEntero e, Object param) {
		return null;
	}

	@Override
	public Object visit(TipoError e, Object param) {
		return null;
	}

	@Override
	public Object visit(TipoFloat32 f, Object param) {
		return null;
	}

	@Override
	public Object visit(TipoFuncion f, Object param) {
		f.getRetorno().accept(this, param);
		for (DefVariable v: f.getArgumentos())
			v.accept(this, param);
		return null;
	}

	@Override
	public Object visit(TipoStruct s, Object param) {
		for (Campo c: s.getCampos())
			c.accept(this, param);
		return null;
	}

	@Override
	public Object visit(TipoVoid v, Object param) {
		return null;
	}

	@Override
	public Object visit(DefFuncion f, Object param) {
		f.getTipoFuncion().accept(this, param);
		for (Sentencia s: f.getCuerpo())
			s.accept(this, param);
		return null;
	}

	@Override
	public Object visit(DefVariable v, Object param) {
		v.getTipo().accept(this, param);
		return null;
	}

	@Override
	public Object visit(Programa p, Object param) {
		for (Definicion d: p.getDefiniciones())
			d.accept(this, param);
		return null;
	}
}