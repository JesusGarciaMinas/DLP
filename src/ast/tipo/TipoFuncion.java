package ast.tipo;

import java.util.ArrayList;
import java.util.List;

import ast.definicion.DefVariable;
import ast.nodo.AbstractNodoAST;

public class TipoFuncion extends AbstractNodoAST implements Tipo {

	private Tipo retorno;
	private List<DefVariable> argumentos;

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
}