package generaciondecodigo;

import java.io.FileWriter;
import java.io.IOException;

import ast.tipo.Tipo;

public class CodeGenerator {

	private FileWriter output;
	private int contador;

	public CodeGenerator(String input, String output) {
		try {
			this.output = new FileWriter(output);
			saltoDeCarro();
			source(input);
			saltoDeCarro();
			contador = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Metodos auxiliares

	public void saltoDeCarro() throws IOException {
		output.write("\n");
	}

	private void tabulador() throws IOException {
		output.write("\t");
	}

	public void write(String orden) throws IOException {
		output.write(orden);
	}

	public void tabCarro(String orden) throws IOException {
		tabulador();
		output.write(orden);
		saltoDeCarro();
	}

	public void cerrar() throws IOException {
		output.close();
	}
	
	public int getLabels(int inc) {
		int tmp = contador;
		contador = contador+ inc;
		return tmp;
		
	}

	// Instrucciones Push

	/**
	 * Apila el caracter (1 byte) en la pila
	 */
	public void push(char c) throws IOException {
		tabCarro("pushb\t" + (int) c);
	}

	/**
	 * Apila el entero (2 bytes) en la pila
	 */
	public void push(int i) throws IOException {
		tabCarro("push\t" + i);
	}

	/**
	 * Apila el número real (4 bytes) en la pila
	 */
	public void push(float f) throws IOException {
		tabCarro("pushf\t" + f);
	}

	/**
	 * Apila la dirección (entero, 2 bytes) en la pila
	 */
	public void pushdir(int dir) throws IOException {
		tabCarro("pusha\t" + dir);
	}

	/**
	 * Apila el valor del registro bp (2 bytes)
	 */
	public void pushBP() throws IOException {
		tabCarro("push\tbp");
	}

	// Instrucciones load y store

	/**
	 * Desapila una direccion de memoria de la pila (2 bytes). A continuación, apila
	 * en la pila el contenido (1, 2 or 4 bytes) de la direccion extraida en el paso
	 * anterior.
	 */
	public void load(char suf) throws IOException {
		tabCarro("load" + suf);
	}

	/**
	 * Desapila de la pila 1, 2 o 4 bytes. A continuación, desapila de la pila una
	 * dirección de memoria (2 bytes). Se sustituye el contenido de la direcciónn de
	 * memoria con el valor extraido en el primer paso.
	 */
	public void store(char c) throws IOException {
		tabCarro("store" + c);
	}

	// Instrucciones pop y dup

	/**
	 * Desapila 1, 2 o 4 bytes, respectivamente, de la pila
	 */
	public void pop(char c) throws IOException {
		tabCarro("pop" + c);
	}

	/**
	 * Duplica 1, 2 o 4 bytes, respectivamente, del tope de la pila
	 */
	public void dup(String suf) throws IOException {
		tabCarro("dup" + suf);
	}

	// Instrucciones aritmeticas

	/**
	 * Para suma
	 */
	public void add(char suffix) throws IOException {
		tabCarro("add" + suffix);
	}

	/**
	 * Para resta
	 */
	public void sub(char suf) throws IOException {
		tabCarro("sub" + suf);
	}

	/**
	 * Para multiplicacion
	 */
	public void mul(char suf) throws IOException {
		tabCarro("mul" + suf);
	}

	/**
	 * Para division
	 */
	private void div(char suf) throws IOException {
		tabCarro("div" + suf);
	}

	/**
	 * Para modulo
	 */
	private void mod(char suf) throws IOException {
		tabCarro("mod" + suf);
	}

	public void aritmetica(String operando, char suffix) throws IOException {
		switch (operando) {
		case "+":
			add(suffix);
			break;
		case "-":
			sub(suffix);
			break;
		case "*":
			mul(suffix);
			break;
		case "/":
			div(suffix);
			break;
		case "%":
			mod(suffix);
			break;
		}
	}

	// Instrucciones de comparacion

	/**
	 * Para comparación "mayor que"
	 */
	private void gt(char suf) throws IOException {
		tabCarro("gt" + suf);
	}

	/**
	 * Para comparación "menor que"
	 */
	private void lt(char suf) throws IOException {
		tabCarro("lt" + suf);
	}

	/**
	 * Para comparación "mayor o igual que"
	 */
	private void ge(char suf) throws IOException {
		tabCarro("ge" + suf);
	}

	/**
	 * Para comparación "menor o igual que"
	 */
	private void le(char suf) throws IOException {
		tabCarro("le" + suf);
	}

	/**
	 * Para comparación "igual que"
	 */
	private void eq(char suf) throws IOException {
		tabCarro("eq" + suf);
	}

	/**
	 * Para comparación "no igual que"
	 */
	private void ne(char suf) throws IOException {
		tabCarro("ne" + suf);
	}

	/**
	 * Desapilan dos operandos, realizan la operacion y apilan el resultado.
	 */
	public void comparacion(String operando, char c) throws IOException {
		switch (operando) {
		case ">":
			gt(c);
			break;
		case "<":
			lt(c);
			break;
		case ">=":
			ge(c);
			break;
		case "<=":
			le(c);
			break;
		case "==":
			eq(c);
			break;
		case "!=":
			ne(c);
			break;
		}
	}

	// Instrucciones logicas

	/**
	 * Para la operacion logica "and"
	 */
	private void and() throws IOException {
		tabCarro("and");
	}

	/**
	 * Para la operacion logica "or"
	 */
	private void or() throws IOException {
		tabCarro("or");
	}

	/**
	 * Para la operacion logica "not"
	 */
	public void not() throws IOException {
		tabCarro("not");
	}

	/**
	 * Desapilan dos operandos, realizan la operacion y apilan el resultado.
	 */
	public void logica(String operando) throws IOException {
		switch (operando) {
		case "&&":
			and();
			break;
		case "||":
			or();
			break;
		}
	}

	// Instrucciones input y output

	/**
	 * Desapila un valor de la pila y lo muestra en la consola
	 */
	public void out(char c) throws IOException {
		tabCarro("out" + c);
	}

	/**
	 * Lee un valor por teclado y lo apila en la pila
	 */
	public void in(char c) throws IOException {
		tabCarro("in" + c);
	}

	// Instrucciones de conversion

	/**
	 * b2i Desapila un caracter y lo apila como entero.
	 * i2f Desapila un entero y lo apila como un número real.
	 * f2i Desapila un número real y lo apila como entero.
	 * i2b Desapila un entero y lo apila como carácter.
	 */
	public void convert(Tipo orig, Tipo fin) throws IOException {
		if (orig.getClass() == fin.getClass())
			return;
		else if (orig.suffix() == 'b' && fin.suffix() == 'f') {
			tabCarro("b2i");
			tabCarro("i2f");
		} else if (orig.suffix() == 'f' && fin.suffix() == 'b') {
			tabCarro("f2i");
			tabCarro("i2b");
		} else {
			tabCarro(orig.suffix() + "2" + fin.suffix());
		}
	}

	// Instrucciones de salto

	/**
	 * Salta (incondicionalmente) a la etiqueta especificada como parametro.
	 */
	public void jmp(String etiqueta) throws IOException {
		tabCarro("jmp\t" + etiqueta);
	}

	/**
	 * Toma un entero de la pila y salta a la etiqueta si el valor extraido es cero.
	 */
	public void jz(String etiqueta) throws IOException {
		tabCarro("jz\t" + etiqueta);
	}

	/**
	 * Toma un entero de la pila y salta a la etiqueta si el valor extraido no es cero.
	 */
	public void jnz(String etiqueta) throws IOException {
		tabCarro("jnz\t" + etiqueta);
	}

	/**
	 * Define una etiqueta para realizar saltos e invocaciones (funciones/a funciones).
	 */
	public void id(String etiqueta) throws IOException {
		output.write(etiqueta + ":\n");
	}

	// Funciones

	/**
	 * Invoca la funcion <id>
	 */
	public void call(String nombre) throws IOException {
		tabCarro("call\t" + nombre);
	}

	/**
	 * Reserva <int_constant> bytes en el tope de la pila
	 */
	public void enter(int numBytes) throws IOException {
		tabCarro("enter " + numBytes);
	}

	/**
	 * Termina la ejecución del programa
	 */
	private void halt() throws IOException {
		output.write("\thalt");
		saltoDeCarro();
	}

	/**
	 * Retorna desde la invocación a una función
	 */
	public void ret(int bytesRetorno, int bytesLocales, int bytesParam) throws IOException {
		tabCarro(" ret " + bytesRetorno + ", " + bytesLocales + ", " + bytesParam);
	}

	// Llamada principal del programa

	private void callmain() throws IOException {
		output.write("\tcall main");
		saltoDeCarro();
	}

	public void callmainHalt() throws IOException {
		output.write("\n' Invocation to the main function");
		saltoDeCarro();
		callmain();
		halt();
		saltoDeCarro();
	}

	// Información de debub

	/**
	 * Permite al IDE de MAPL asociaciar el codigo ensamblador con el código en alto
	 * nivel.
	 */
	private void source(String input) throws IOException {
		output.write("#source \"" + input + "\"");
		saltoDeCarro();
	}

	/**
	 * Permite al IDE de MAPL asociaciar el codigo ensamblador con la
	 * correspondiente sentencia en alto nivel.
	 */
	public void line(int numero) throws IOException {
		output.write("#line " + numero);
		saltoDeCarro();
	}
}