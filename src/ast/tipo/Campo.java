package ast.tipo;

import visitor.Visitor;

public class Campo extends AbstractTipo {

	private String nombreCampo;
	private Tipo tipoCampo;
	private int offset;

	public Campo(int linea, int columna, String nombreCampo, Tipo tipoCampo) {
		super(linea, columna);
		setNombreCampo(nombreCampo);
		setTipoCampo(tipoCampo);
	}

	public String getNombreCampo() {
		return nombreCampo;
	}

	private void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	public Tipo getTipoCampo() {
		return tipoCampo;
	}

	private void setTipoCampo(Tipo tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	@Override
	public String toString() {
		return "Campo [nombreCampo=" + nombreCampo + ", tipoCampo=" + tipoCampo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombreCampo == null) ? 0 : nombreCampo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campo other = (Campo) obj;
		if (nombreCampo == null) {
			if (other.nombreCampo != null)
				return false;
		} else if (!nombreCampo.equals(other.nombreCampo))
			return false;
		return true;
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public int numeroDeBytes() {
		return tipoCampo.numeroDeBytes();
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}