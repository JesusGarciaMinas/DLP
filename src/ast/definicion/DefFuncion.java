package ast.definicion;

import java.util.ArrayList;
import java.util.List;

import ast.nodo.AbstractNodoAST;
import ast.sentencia.Sentencia;
import ast.tipo.Tipo;
import visitor.Visitor;

public class DefFuncion extends AbstractNodoAST implements Definicion {

	private Tipo tipo;
	private String nombreFuncion;
	private List<Sentencia> cuerpo;
	private int localVarBytes;

	public DefFuncion(int linea, int columna, Tipo tipo, String nombreFuncion, List<Sentencia> cuerpo) {
		super(linea, columna);
		setTipo(tipo);
		setNombreFuncion(nombreFuncion);
		setCuerpo(cuerpo);
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
		return "DefinicionFuncion [tipo=" + tipo + ", nombreFuncion=" + nombreFuncion + ", cuerpo=" + cuerpo + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}

	@Override
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	public int getLocalVarBytes() {
		return localVarBytes;
	}
	
	public void setLocalVarBytes(int localVarBytes) {
		this.localVarBytes = localVarBytes;
	}
}