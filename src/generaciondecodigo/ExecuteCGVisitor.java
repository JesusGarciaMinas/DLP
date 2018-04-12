package generaciondecodigo;

import java.io.IOException;

import ast.*;
import ast.definicion.*;
import ast.expresion.*;
import ast.sentencia.*;
import ast.tipo.*;
import visitor.AbstractCGVisitor;

public class ExecuteCGVisitor extends AbstractCGVisitor {

	private CodeGenerator cg;

	private ValueCGVisitor vcg;

	private AddressCGVisitor acg;

	public ExecuteCGVisitor(String input, String output) {
		super();
		cg = new CodeGenerator(input, output);
		vcg = new ValueCGVisitor(cg);
		acg = new AddressCGVisitor(cg);
	}

	@Override
	public Object visit(Programa p, Object param) {
		for (Definicion def : p.getDefiniciones()) {
			if (def instanceof DefVariable)
				def.accept(this, param);
		}
		try {
			cg.callmainHalt();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Definicion def : p.getDefiniciones()) {
			if (def instanceof DefFuncion)
				def.accept(this, param);
		}

		try {
			cg.cerrar();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object visit(Escritura w, Object param) {

		try {
			cg.saltoDeCarro();
			cg.line(w.getLinea());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Expresion exp : w.getExpresiones()) {
			try {
				cg.tabCarro("' * Write");
			} catch (IOException e) {
				e.printStackTrace();
			}
			exp.accept(vcg, param);
			try {
				cg.out(exp.getTipoExpresion().suffix());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Object visit(Lectura l, Object param) {

		try {
			cg.saltoDeCarro();
			cg.line(l.getLinea());
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Expresion exp : l.getExpresiones()) {
			try {
				cg.tabCarro("' * Read");
			} catch (IOException e) {
				e.printStackTrace();
			}
			exp.accept(acg, param);
			try {
				cg.in(exp.getTipoExpresion().suffix());
				cg.store(exp.getTipoExpresion().suffix());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Object visit(Asignacion a, Object param) {
		try {
			cg.saltoDeCarro();
			cg.line(a.getLinea());
		} catch (IOException e) {
			e.printStackTrace();
		}

		a.getExpLeft().accept(acg, param);
		a.getExpRight().accept(vcg, param);
		try {
			cg.convert(a.getExpRight().getTipoExpresion(), a.getExpLeft().getTipoExpresion());
			cg.store(a.getExpLeft().getTipoExpresion().suffix());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object visit(DefVariable v, Object param) {
		try {
			cg.tabCarro("' * var " + v.getNombre() + " " +  v.getTipo().toString() + " (offset " + v.getOffset() + ")");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object visit(DefFuncion f, Object param) {
		TipoFuncion tipo = ((TipoFuncion) f.getTipo());
		try {
			cg.saltoDeCarro();
			cg.line(f.getCuerpo().get(0).getLinea()-1);
			cg.saltoDeCarro();
			cg.write(" " + f.getNombre() + ":");
			cg.saltoDeCarro();
			cg.tabCarro("' * Parameters");
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (DefVariable def : tipo.getArgumentos())
			def.accept(this, param);

		try {
			cg.tabCarro("' * Local variables");
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Sentencia sen : f.getCuerpo()) {
			if (sen instanceof DefVariable)
				sen.accept(this, param);
		}
		try {
			cg.enter(f.getLocalVarBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Sentencia sen : f.getCuerpo()) {
			if (!(sen instanceof DefVariable))
				sen.accept(this, param);
		}

		if (tipo.getRetorno() instanceof TipoVoid) {
			try {
				cg.ret(0, f.getLocalVarBytes(), tipo.getParamVarBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}