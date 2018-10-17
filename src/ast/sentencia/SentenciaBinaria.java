package ast.sentencia;

import ast.expresion.Expresion;
import ast.nodo.AbstractNodoAST;
import ast.tipo.Tipo;
import visitor.Visitor;

public class SentenciaBinaria extends AbstractNodoAST implements Sentencia {

	private Expresion expLeft, expRight;
	private String operador;
	private Tipo tipoSentencia;

	public SentenciaBinaria(int linea, int columna, Expresion expLeft, String operador, Expresion expRight) {
		super(linea, columna);
		setExpLeft(expLeft);
		setExpRight(expRight);
		setOperador(operador);
	}

	public Expresion getExpLeft() {
		return expLeft;
	}

	private void setExpLeft(Expresion expLeft) {
		this.expLeft = expLeft;
	}

	public Expresion getExpRight() {
		return expRight;
	}

	private void setExpRight(Expresion expRight) {
		this.expRight = expRight;
	}

	public String getOperador() {
		return operador;
	}

	private void setOperador(String operador) {
		this.operador = operador;
	}

	@Override
	public String toString() {
		return "SentenciaBinaria [expLeft=" + expLeft + ", operador=" + operador + ", expRight=" + expRight + "]";
	}

	@Override
	public Object accept(Visitor visitor, Object param) {
		return visitor.visit(this, param);
	}
	

	public Tipo getTipoSentencia() {
		return tipoSentencia;
	}

	public void setTipoSentencia(Tipo tipoSentencia) {
		this.tipoSentencia = tipoSentencia;
	}
}