package generaciondecodigo;

import java.io.IOException;

import ast.definicion.*;
import ast.expresion.*;
import visitor.AbstractCGVisitor;

public class AddressCGVisitor extends AbstractCGVisitor {

	private CodeGenerator cg;

	public AddressCGVisitor(CodeGenerator cg) {
		super();
		this.cg = cg;
	}

	@Override
	public Object visit(Variable var, Object param) {
		DefVariable def = (DefVariable) var.getDefinicion();
		try {
			if (def.getAmbito() == 0) {
				cg.pushdir(def.getOffset());
			} else {
				cg.pushBP();
				cg.push(def.getOffset());
				cg.add('i');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}