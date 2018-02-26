package ast.expresion;

public class LogicaBinaria extends ExpresionBinaria {

	public LogicaBinaria(int linea, int columna, Expresion expLeft, String operador, Expresion expRight) {
		super(linea, columna, expLeft, operador, expRight);
	}

	@Override
	public String toString() {
		return "Logica [" + super.toString() + "]";
	}
}