package ast.tipo;

import ast.nodo.AbstractNodoAST;

public class Campo extends AbstractNodoAST implements Tipo {
	
	private String nombreCampo;
	private Tipo tipoCampo;

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
}