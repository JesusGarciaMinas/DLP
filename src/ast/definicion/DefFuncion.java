package ast.definicion;

import java.util.ArrayList;
import java.util.List;

import ast.nodo.AbstractNodoAST;
import ast.sentencia.Sentencia;
import ast.tipo.TipoFuncion;
import visitor.Visitor;

public class DefFuncion extends AbstractNodoAST implements Definicion {

	private TipoFuncion tipoFuncion;
	private String nombreFuncion;
	private List<Sentencia> cuerpo;

	public DefFuncion(int linea, int columna, TipoFuncion tipoFuncion, String nombreFuncion,
			List<Sentencia> cuerpo) {
		super(linea, columna);
		setTipoFuncion(tipoFuncion);
		setNombreFuncion(nombreFuncion);
		setCuerpo(cuerpo);
	}

	public TipoFuncion getTipoFuncion() {
		return tipoFuncion;
	}

	private void setTipoFuncion(TipoFuncion tipoFuncion) {
		this.tipoFuncion = tipoFuncion;
	}

	public String getNombre() {
		return nombreFuncion;
	}

	private void setNombreFuncion(String nombreFuncion) {
		this.nombreFuncion = nombreFuncion;
	}

	public List<Sentencia> getCuerpo() {
		return cuerpo;
	}

	private void setCuerpo(List<Sentencia> cuerpo) {
		this.cuerpo = new ArrayList<Sentencia>(cuerpo);
	}

	@Override
	public String toString() {
		return "DefinicionFuncion [tipoFuncion=" + tipoFuncion + ", nombreFuncion=" + nombreFuncion + ", cuerpo="
				+ cuerpo + "]";
	}
	
	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
}