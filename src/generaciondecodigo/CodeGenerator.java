package generaciondecodigo;

import java.io.FileWriter;
import java.io.IOException;

import ast.tipo.Tipo;

public class CodeGenerator {

	private FileWriter output;

	public CodeGenerator(String input, String output) {
		try {
			this.output = new FileWriter(output);
			saltoDeCarro();
			source(input);
			saltoDeCarro();
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
	
	public void write (String orden) throws IOException {
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

	// Instrucciones Push

	public void push(char c) throws IOException {
		tabCarro("pushb\t" + (int) c);
	}

	public void push(int i) throws IOException {
		tabCarro("pushi\t" + i);
	}

	public void push(double f) throws IOException {
		tabCarro("pushf\t" + f);
	}

	public void pushdir(int dir) throws IOException {
		tabCarro("pusha\t" + dir);
	}

	public void pushBP() throws IOException {
		tabCarro("push\tbp");
	}

	// Instrucciones load y store

	public void load(char suf) throws IOException {
		tabCarro("load" + suf);
	}

	public void store(char c) throws IOException {
		tabCarro("store" + c);
	}

	// Instrucciones pop y dup

	public void pop(String suf) throws IOException {
		tabCarro("pop" + suf);
	}

	public void dup(String suf) throws IOException {
		tabCarro("dup" + suf);
	}

	// Instrucciones aritmeticas

	public void add(char suffix) throws IOException {
		tabCarro("add" + suffix);
	}

	public void sub(char suf) throws IOException {
		tabCarro("sub" + suf);
	}

	private void mul(char suf) throws IOException {
		tabCarro("mul" + suf);
	}

	private void div(char suf) throws IOException {
		tabCarro("div" + suf);
	}

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

	private void gt(char suf) throws IOException {
		tabCarro("gt" + suf);
	}

	private void lt(char suf) throws IOException {
		tabCarro("lt" + suf);
	}

	private void ge(char suf) throws IOException {
		tabCarro("ge" + suf);
	}

	private void le(char suf) throws IOException {
		tabCarro("le" + suf);
	}

	private void eq(char suf) throws IOException {
		tabCarro("eq" + suf);
	}

	private void ne(char suf) throws IOException {
		tabCarro("ne" + suf);
	}

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

	private void and() throws IOException {
		tabCarro("and");
	}

	private void or() throws IOException {
		tabCarro("or");
	}

	public void not() throws IOException {
		tabCarro("not");
	}

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

	public void out(char c) throws IOException {
		tabCarro("out" + c);
	}

	public void in(char c) throws IOException {
		tabCarro("in" + c);
	}

	// Instrucciones de conversion

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

	public void jmp(String etiqueta) throws IOException {
		tabCarro("jmp\t" + etiqueta);
	}

	public void jz(String etiqueta) throws IOException {
		tabCarro("jz\t" + etiqueta);
	}

	public void jnz(String etiqueta) throws IOException {
		tabCarro("jnz\t" + etiqueta);
	}

	public void id(String etiqueta) throws IOException {
		output.write(" " + etiqueta + ":");
	}

	// Funciones

	public void call(String nombre) throws IOException {
		tabCarro("call\t" + nombre);
	}

	public void enter(int numBytes) throws IOException {
		tabCarro("enter\t" + numBytes);
	}

	private void halt() throws IOException {
		output.write("halt");
		saltoDeCarro();
	}

	public void ret(int bytesRetorno, int bytesLocales, int bytesParam) throws IOException {
		tabCarro("ret\t" + bytesRetorno + ", " + bytesLocales + ", " + bytesParam);
	}

	// Llamada principal del programa

	private void callmain() throws IOException {
		output.write("call main");
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

	private void source(String input) throws IOException {
		output.write("#source\t\"" + input + "\"");
		saltoDeCarro();
	}

	public void line(int numero) throws IOException {
		output.write("#line\t" + numero);
		saltoDeCarro();
	}
}