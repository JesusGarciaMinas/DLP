package semantico;

import java.util.ArrayList;
import java.util.List;

import ast.definicion.DefFuncion;
import ast.expresion.*;
import ast.sentencia.*;
import ast.tipo.*;
import visitor.AbstractVisitor;

public class VisitorComprobacionTipos extends AbstractVisitor {
	// Visit literales -> asignar tipo con un singleton
	@Override
	public Object visit(LiteralChar c, Object visit) {
		super.visit(c, visit);
		c.setTipoExpresion(TipoChar.getInstancia());
		return null;
	}

	@Override
	public Object visit(LiteralEntero e, Object visit) {
		super.visit(e, visit);
		e.setTipoExpresion(TipoEntero.getInstancia());
		return null;
	}

	@Override
	public Object visit(LiteralDecimal d, Object visit) {
		super.visit(d, visit);
		d.setTipoExpresion(TipoDecimal.getInstancia());
		return null;
	}

	// Visit variable -> asignar tipo defVrariable Crear definicion en el otro
	// visitor
	@Override
	public Object visit(Variable v, Object visit) {
		super.visit(v, visit);
		v.setTipoExpresion(v.getDefinicion().getTipo());
		if (v.getTipoExpresion() == null)
			v.setTipoExpresion(new TipoError(v, "El tipo de la variable es incorrecto"));
		return null;
	}

	// Visit While -> Comprobar que condición sea de tipo lógico Llamar al accept de
	// condicion
	// despues hacer un if preguntando al tipo si es logico
	// Si no lo es TipoError() Asignarselo al tipo de la condición
	// for sentencia s: w.cuerpo s.accept(this, param);} return null;
	@Override
	public Object visit(SentWhile w, Object visit) {
		w.getCondicion().accept(this, visit);
		if (!w.getCondicion().getTipoExpresion().esLogico())
			w.getCondicion().setTipoExpresion(new TipoError(w, "La condición del While no es una expresión lógica"));
		for (Sentencia s : w.getSentWhile()) {
			s.accept(this, visit);
		}
		return null;
	}

	// visit if -> lo mismo que while
	@Override
	public Object visit(SentIf i, Object visit) {
		boolean retornoIf = false, retornoElse = false;
		i.getCondicion().accept(this, visit);
		if (!i.getCondicion().getTipoExpresion().esLogico())
			i.getCondicion().setTipoExpresion(new TipoError(i, "La condición del if no es una expresión lógica"));
		for (Sentencia s : i.getSentIf()) {
			Object retorno = s.accept(this, visit);
			if (retorno != null) {
				retornoIf = (boolean) retorno;
			}
		}
		for (Sentencia s : i.getSentElse()) {
			Object retorno = s.accept(this, visit);
			if (retorno != null) {
				retornoElse = (boolean) retorno;
			}
		}
		return retornoIf && retornoElse;
	}

	// visit aritmetica -> llamamos al accept de los 2 operandos hacer un if de
	// aritmetica de los 2 tipos
	// Asignar tipo a aritmetica
	// if a.tipo == null a.tipo= new Tipo Error(); Copiar aritmetica de TipoEntero a
	// Char y Decimal
	@Override
	public Object visit(Aritmetica a, Object visit) {
		super.visit(a, visit);
		a.setTipoExpresion(a.getExpLeft().getTipoExpresion().aritmetica(a.getExpRight().getTipoExpresion()));
		if (a.getTipoExpresion() == null)
			a.setTipoExpresion(new TipoError(a, "Tipos incompatibles en una operación"));
		return null;
	}

	// visit MenosUnario aceptar el operando Hacer EsAritmetica del operando (sin
	// parámetro)
	// mu.tipo = mu.op.tipo.aritmetica(); if (mu.tipo==null) mu.tipo = new TipoError
	@Override
	public Object visit(MenosUnario u, Object visit) {
		super.visit(u, visit);
		u.setTipoExpresion(u.getOperando().getTipoExpresion().aritmetica());
		if (u.getTipoExpresion() == null)
			u.setTipoExpresion(new TipoError(u, "No es un tipo válido para el menos unario"));
		return null;
	}

	// visit comparacion Igual que aritmetica //Si es el mismo tipo Entero sino
	// error
	@Override
	public Object visit(Comparacion c, Object visit) {
		super.visit(c, visit);
		c.setTipoExpresion(c.getExpLeft().getTipoExpresion().comparacion(c.getExpRight().getTipoExpresion()));
		if (c.getTipoExpresion() == null)
			c.setTipoExpresion(new TipoError(c, "No son comparables ambos operandos"));
		return null;
	}

	// Visit Logica //Si es tipo entero sino error
	@Override
	public Object visit(LogicaBinaria l, Object visit) {
		super.visit(l, visit);
		l.setTipoExpresion(l.getExpLeft().getTipoExpresion().logica(l.getExpRight().getTipoExpresion()));
		if (l.getTipoExpresion() == null)
			l.setTipoExpresion(new TipoError(l, "Ambos operandos son incompatibles para una expresión lógica"));
		return null;
	}

	@Override
	public Object visit(LogicaUnaria l, Object visit) {
		super.visit(l, visit);
		if (l.getExpresion().getTipoExpresion().esLogico())
			l.setTipoExpresion(l.getExpresion().getTipoExpresion());
		else
			l.setTipoExpresion(new TipoError(l, "No se puede realizar un NOT sobre esta expresión"));
		return null;
	}

	// Visit accesoCampo esPunto ac.tipo = ac.op.tipo.punto(ac.campo) if null
	// ac.tipo = TipoError
	@Override
	public Object visit(AccesoCampo c, Object visit) {
		super.visit(c, visit);
		c.setTipoExpresion(c.getExpresion().getTipoExpresion().punto(c.getNombre()));
		if (c.getTipoExpresion() == null)
			c.setTipoExpresion(new TipoError(c, "El acceso a campo no ha encontrado el campo solicitado"));
		return null;
	}

	// Visit accesoArray esCorchetes
	@Override
	public Object visit(AccesoArray a, Object visit) {
		super.visit(a, visit);
		a.setTipoExpresion(a.getNombre().getTipoExpresion().corchetes(a.getTamaño().getTipoExpresion()));
		if (a.getTipoExpresion() == null)
			a.setTipoExpresion(new TipoError(a, "El acceso a array presenta una incompatibilidad de tipos"));
		return null;
	}

	// Visit cast cast
	@Override
	public Object visit(Cast c, Object visit) {
		super.visit(c, visit);
		c.setTipoExpresion(c.getExpresion().getTipoExpresion().cast(c.getTipo()));
		if (c.getTipoExpresion() == null)
			c.setTipoExpresion(new TipoError(c, "El tipo del casteo realizado es incorrecto"));
		return null;
	}

	// Visit asignacion promocionaA
	@Override
	public Object visit(Asignacion a, Object visit) {
		super.visit(a, visit);
		a.getExpLeft()
				.setTipoExpresion(a.getExpRight().getTipoExpresion().promocionaA(a.getExpLeft().getTipoExpresion()));
		if (a.getExpLeft().getTipoExpresion() == null)
			a.getExpLeft().setTipoExpresion(new TipoError(a, "La asignación presenta una incompatibilidad de tipos"));
		return null;
	}

	// Visit invocacionFuncion parentesis
	@Override
	public Object visit(InvocacionFuncion f, Object visit) {
		f.getNombre().accept(this, visit);
		List<Tipo> lista = new ArrayList<Tipo>();
		for (Expresion e : f.getArgumentos()) {
			e.accept(this, visit);
			lista.add(e.getTipoExpresion());
		}
		f.setTipoExpresion(f.getNombre().getTipoExpresion().parentesis(lista));
		if (f.getTipoExpresion() == null) {
			f.setTipoExpresion(new TipoError(f, "La invocación a función tiene unos tipos incorrectos"));
		}
		return null;
	}

	@Override
	public Object visit(DefFuncion f, Object visit) {
		boolean esRetorno = false;
		TipoFuncion tipofuncion = (TipoFuncion) f.getTipo();
		f.getTipo().accept(this, visit);
		for (Sentencia s : f.getCuerpo()) {
			Object retorno = s.accept(this, tipofuncion);
			if (retorno != null) {
				esRetorno = (boolean) retorno;
			}
		}
		if (esRetorno && tipofuncion.getRetorno() == TipoVoid.getInstancia())
			new TipoError(f, "No se puede devolver nada en una función de tipo void");
		else if (!esRetorno && tipofuncion.getRetorno() != TipoVoid.getInstancia())
			new TipoError(f, "La función debe devolver algún objeto para que sea válida");
		return null;
	}

	@Override
	public Object visit(SentReturn r, Object visit) {
		super.visit(r, visit);
		TipoFuncion tipofuncion = (TipoFuncion) visit;
		if (r.getExpresion().getTipoExpresion().promocionaA(tipofuncion.getRetorno()) == null) {
			new TipoError(r, "El tipo de retorno no coincide con el tipo de la función");
		}
		return true;
	}

	@Override
	public Object visit(SentenciaBinaria b, Object visit) {
		super.visit(b, visit);
		b.setTipoSentencia(b.getExpLeft().getTipoExpresion().sentenciasExamen(b.getExpRight().getTipoExpresion()));
		if (b.getTipoSentencia() == null) {
			if (!(b.getExpLeft().getTipoExpresion() instanceof TipoEntero))
				b.setTipoSentencia(new TipoError(b, "El tipo de la izquierda es " + b.getExpLeft().getTipoExpresion()
						+ " por lo que no se puede realizar esta operación"));
			else
				b.setTipoSentencia(new TipoError(b, "El tipo de la derecha es " + b.getExpRight().getTipoExpresion()
						+ " por lo que no se puede realizar esta operación"));
		}
		return null;
	}

	@Override
	public Object visit(ExpresionExamen e, Object visit) {
		super.visit(e, visit);
		e.setTipoExpresion(e.getExpLeft().getTipoExpresion().sentenciasExamen(e.getExpRight().getTipoExpresion()));
		if (e.getTipoExpresion() == null) {
			if (!(e.getExpLeft().getTipoExpresion() instanceof TipoEntero))
				e.setTipoExpresion(new TipoError(e, "El tipo de la izquierda es " + e.getExpLeft().getTipoExpresion()
						+ " por lo que no se puede realizar esta operación"));
			else
				e.setTipoExpresion(new TipoError(e, "El tipo de la derecha es " + e.getExpRight().getTipoExpresion()
						+ " por lo que no se puede realizar esta operación"));
		}
		return null;
	}
}