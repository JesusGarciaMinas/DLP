package generaciondecodigo;

import java.io.IOException;

import ast.expresion.*;
import ast.tipo.TipoEntero;
import visitor.AbstractCGVisitor;

public class ValueCGVisitor extends AbstractCGVisitor {

	private CodeGenerator cg;

	private AddressCGVisitor acg;

	public ValueCGVisitor(CodeGenerator cg) {
		super();
		this.cg = cg;
		acg = new AddressCGVisitor(cg);
	}

	@Override
	public Object visit(LiteralChar c, Object param) {
		try {
			cg.push(tipoChar(c));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private char tipoChar(LiteralChar c) {
		char v = '0';
		String ch = c.getValor().replaceAll("'", "");
		if (ch.length() == 1) {
			v = ch.charAt(0);
		} else if (ch.equals("\\t")) {
			v = '\t';
		} else if (ch.equals("\\n")) {
			v = '\n';
		} else {
			v = (char) Integer.parseInt(ch.substring(1, ch.length() - 1));
		}
		return v;
	}

	@Override
	public Object visit(LiteralEntero e, Object param) {
		try {
			cg.push(e.getValor());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Object visit(LiteralDecimal r, Object param) {
		try {
			cg.push(r.getValor());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object visit(Variable v, Object param) {
		try {
			v.accept(acg, param);
			cg.load(v.getTipoExpresion().suffix());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object visit(Aritmetica a, Object param) {
		a.getExpLeft().accept(this, param);
		try {
			cg.convert(a.getExpLeft().getTipoExpresion(), a.getTipoExpresion());
			a.getExpRight().accept(this, param);
			cg.convert(a.getExpRight().getTipoExpresion(), a.getTipoExpresion());
			cg.aritmetica(a.getOperador(), a.getTipoExpresion().suffix());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object visit(Comparacion c, Object param) {
		c.getExpLeft().accept(this, param);
		try {
			cg.convert(c.getExpLeft().getTipoExpresion(),
					c.getExpLeft().getTipoExpresion().superType(c.getExpRight().getTipoExpresion()));
			c.getExpRight().accept(this, param);
			cg.convert(c.getExpRight().getTipoExpresion(),
					c.getExpRight().getTipoExpresion().superType(c.getExpLeft().getTipoExpresion()));
			cg.comparacion(c.getOperador(), c.getTipoExpresion().suffix());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object visit(LogicaBinaria b, Object param) {

		try {
			b.getExpLeft().accept(this, param);
			cg.convert(b.getExpLeft().getTipoExpresion(), b.getTipoExpresion());
			b.getExpRight().accept(this, param);
			cg.convert(b.getExpRight().getTipoExpresion(), b.getTipoExpresion());
			cg.logica(b.getOperador());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object visit(LogicaUnaria u, Object param) {
		try {
			u.getExpresion().accept(this, param);
			cg.not();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object visit(Cast c, Object param) {
		try {
			c.getExpresion().accept(this, param);
			cg.convert(c.getExpresion().getTipoExpresion(), c.getTipoExpresion());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object visit(MenosUnario u, Object param) {
		try {
			cg.push(0);
			cg.convert(TipoEntero.getInstancia(), u.getTipoExpresion());
			u.getTipoExpresion().accept(this, param);
			cg.sub(u.getTipoExpresion().suffix());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}