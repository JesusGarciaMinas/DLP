package semantico;

import ast.definicion.*;
import ast.expresion.Variable;
import ast.tipo.TipoError;
import tablasimbolos.TablaSimbolos;
import visitor.AbstractVisitor;

public class VisitorIdentificacion extends AbstractVisitor {

	private TablaSimbolos ts = new TablaSimbolos();

	@Override
	public Object visit(DefFuncion f, Object param) {
		if (!ts.insertar(f))
			new TipoError(f, "Funcion " + f + " duplicada");
		ts.set();
		super.visit(f, param);
		ts.reset();
		return null;
	}

	public Object visit(DefVariable v, Object param) {
		if (!ts.insertar(v))
			new TipoError(v, "Variable " + v + " duplicada");
		super.visit(v, param);
		return null;
	}

	@Override
	public Object visit(Variable v, Object param) {
		Definicion def = ts.buscar(v.getNombre());
		if (def == null) {
			v.setDefinicion(new DefVariable(v.getLinea(), v.getColumna(), v.getNombre(),
					new TipoError(v, "Variable " + v + " indefinida")));
		} else
			v.setDefinicion(def);
		super.visit(v, param);
		return null;
	}
}