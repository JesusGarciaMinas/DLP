package ast.tipo;

import java.util.ArrayList;
import java.util.List;

import visitor.Visitor;

public class TipoStruct extends AbstractTipo {

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

	@Override
	public Tipo punto(String nombreCampo) {
		for (Campo c : campos)
			if (c.getNombreCampo().equals(nombreCampo))
				return c.getTipoCampo();
		return null;
	}

	@Override
	public int numeroDeBytes() {
		int sum = 0;
		for (Campo c : campos)
			sum += c.numeroDeBytes();
		return sum;
	}
}