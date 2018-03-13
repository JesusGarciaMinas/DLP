package semantico;

import ast.expresion.*;
import ast.sentencia.*;
import ast.tipo.*;
import visitor.AbstractVisitor;

public class VisitorlValue extends AbstractVisitor {

	@Override
	public Object visit(Asignacion a, Object param) {
		super.visit(a, param);
		if (!a.getExpLeft().islValue())
			a.getExpLeft().setTipoExpresion(
					new TipoError(a.getLinea(), a.getColumna(), "Hay un error de tipo lValue en la asignacion"));
		return null;
	}

	@Override
	public Object visit(AccesoCampo c, Object param) {
		super.visit(c, param);
		c.setlValue(true);
		return null;
	}

	@Override
	public Object visit(AccesoArray a, Object param) {
		super.visit(a, param);
		a.setlValue(true);
		return null;
	}

	@Override
	public Object visit(Variable v, Object param) {
		super.visit(v, param);
		v.setlValue(true);
		return null;
	}

	@Override
	public Object visit(Lectura l, Object param) {
		super.visit(l, param);
		for (Expresion e : l.getExpresiones())
			if (!e.islValue())
				new TipoError(e.getLinea(), e.getColumna(), "Hay un error de tipo lValue en Lectura");
		return null;
	}
}