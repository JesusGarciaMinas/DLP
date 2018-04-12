package generaciondecodigo;

import ast.definicion.*;
import ast.tipo.*;
import visitor.AbstractVisitor;

public class VisitorOffset extends AbstractVisitor {

	private int offsetGlobal = 0;
	private int offsetLocal = 0;

	@Override
	public Object visit(DefVariable v, Object param) {
		if (v.getAmbito() == 0) {
			v.setOffset(offsetGlobal);
			offsetGlobal += v.getTipo().numeroDeBytes();
		}
		if (v.getAmbito() == 1) {
			offsetLocal -= v.getTipo().numeroDeBytes();
			v.setOffset(offsetLocal);
		}
		super.visit(v, param);
		return null;
	}

	@Override
	public Object visit(TipoFuncion f, Object param) {
		f.getRetorno().accept(this, param);
		int offsetParam = 4;
		for (int i = f.getArgumentos().size() - 1; i >= 0; i--) {
			f.getArgumentos().get(i).setOffset(offsetParam);
			offsetParam += f.getArgumentos().get(i).getTipo().numeroDeBytes();
		}
		f.setParamVarBytes(offsetParam - 4);
		return null;
	}

	@Override
	public Object visit(TipoStruct s, Object param) {
		int offsetCampo = 0;
		for (Campo c : s.getCampos()) {
			c.setOffset(offsetCampo);
			offsetCampo += c.getTipoCampo().numeroDeBytes();
		}
		super.visit(s, param);
		return null;
	}

	@Override
	public Object visit(DefFuncion f, Object param) {
		super.visit(f, param);
		f.setLocalVarBytes(-offsetLocal);
		offsetLocal = 0;
		return null;
	}
}