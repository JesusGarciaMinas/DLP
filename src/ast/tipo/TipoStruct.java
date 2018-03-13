package ast.tipo;

import java.util.ArrayList;
import java.util.List;

import ast.nodo.AbstractNodoAST;
import visitor.Visitor;

public class TipoStruct extends AbstractNodoAST implements Tipo {

	private List<Campo> campos;

	public TipoStruct(int linea, int columna, List<Campo> campos) {
		super(linea, columna);
		setCampos(campos);
	}

	public List<Campo> getCampos() {
		return campos;
	}

	private void setCampos(List<Campo> campos) {
		this.campos = new ArrayList<Campo>(campos);
	}

	@Override
	public String toString() {
		return "TipoStruct [campos=" + campos + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}