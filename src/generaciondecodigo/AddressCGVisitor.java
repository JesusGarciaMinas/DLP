package generaciondecodigo;

import java.io.IOException;

import ast.definicion.*;
import ast.expresion.*;
import ast.tipo.TipoEntero;
import visitor.AbstractCGVisitor;

public class AddressCGVisitor extends AbstractCGVisitor {

	private CodeGenerator cg;
	private ValueCGVisitor vcg;

	public AddressCGVisitor(CodeGenerator cg) {
		super();
		this.cg = cg;
	}
	
	public void setVcg(ValueCGVisitor vcg) {
		this.vcg = vcg;
	}

	@Override
	public Object visit(Variable var, Object param) {
		try {
			DefVariable def = (DefVariable) var.getDefinicion();
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

	@Override
	public Object visit(AccesoArray a, Object param) {
		try {
			a.getNombre().accept(this, param);
			a.getTamaño().accept(vcg, param);
			cg.convert(a.getTamaño().getTipoExpresion(), TipoEntero.getInstancia());
			cg.push(a.getTipoExpresion().numeroDeBytes());
			cg.mul('i');
			cg.add('i');
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Object visit(AccesoCampo c, Object param) {
		try {
			c.getExpresion().accept(this, param);
			cg.push(c.getExpresion().getTipoExpresion().get(c.getNombre()).getOffset());
			cg.add('i');
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}