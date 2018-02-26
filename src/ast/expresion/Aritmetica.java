package ast.expresion;

public class Aritmetica extends ExpresionBinaria {

	public Aritmetica(int linea, int columna, Expresion operador1, String operando, Expresion operador2) {
		super(linea, columna, operador1, operando, operador2);
	}

	@Override
	public String toString() {
		return "Aritmetica [toString()=" + super.toString() + "]";
	}
}