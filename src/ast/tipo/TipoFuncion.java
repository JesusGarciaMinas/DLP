package ast.tipo;

import java.util.ArrayList;
import java.util.List;

import ast.definicion.DefVariable;
import visitor.Visitor;

public class TipoFuncion extends AbstractTipo {

	private Tipo retorno;
	private List<DefVariable> argumentos;
	private int paramVarBytes;

	public TipoFuncion(int linea, int columna, Tipo retorno, List<DefVariable> argumentos) {
		super(linea, columna);
		setRetorno(retorno);
		setArgumentos(argumentos);
	}

	public Tipo getRetorno() {
		return retorno;
	}

	public void setRetorno(Tipo retorno) {
		this.retorno = retorno;
	}

	public List<DefVariable> getArgumentos() {
		return argumentos;
	}

	public void setArgumentos(List<DefVariable> argumentos) {
		this.argumentos = new ArrayList<DefVariable>(argumentos);
	}

	@Override
	public String toString() {
		return "TipoFuncion [retorno=" + retorno + ", argumentos=" + argumentos + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public Tipo parentesis(List<Tipo> tipos) {
		if (getArgumentos().size() != tipos.size())
			return null;
		for (int i = 0; i < tipos.size(); i++) {
			if (tipos.get(i).promocionaA(getArgumentos().get(i).getTipo()) == null)
				return null;
		}
		return getRetorno();
	}
	
	@Override
	public int numeroDeBytes() {
		return retorno.numeroDeBytes();
	}

	public int getParamVarBytes() {
		return paramVarBytes;
	}
	
	public void setParamVarBytes (int paramVarBytes) {
		this.paramVarBytes = paramVarBytes;
	}
}