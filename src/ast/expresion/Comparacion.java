package ast.expresion;

public class Comparacion extends ExpresionBinaria {

	public Comparacion(int linea, int columna, Expresion expLeft, String operador, Expresion expRight) {
		super(linea, columna, expLeft, operador, expRight);
	}

	@Override
	public String toString() {
		return "Comparacion [toString()=" + super.toString() + "]";
	}
}