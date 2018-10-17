package src;

import java.io.FileReader;
import java.io.IOException;

import ast.nodo.NodoAST;
import generaciondecodigo.ExecuteCGVisitor;
import generaciondecodigo.VisitorOffset;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import lexico.Lexico;
import sintactico.Parser;
import visitor.Visitor;
import manejadorerrores.ME;
import semantico.*;

public class Main {
	public static void main(String args[]) throws IOException {
		if (args.length < 2) {
			System.err.println("Necesito el archivo de entrada y el nombre del de salida.");
			return;
		}

		FileReader fr = null;
		try {
			fr = new FileReader(args[0]);
		} catch (IOException io) {
			System.err.println("El archivo " + args[0] + " no se ha podido abrir.");
			return;
		}

		// * Creamos léxico y sintáctico
		Lexico lexico = new Lexico(fr);
		Parser parser = new Parser(lexico);
		// * "Parseamos"
		parser.run();

		NodoAST programa = parser.getAST();
		Visitor v1 = new VisitorIdentificacion();
		Visitor v2 = new VisitorlValue();
		Visitor v3 = new VisitorComprobacionTipos();
		
		Visitor v4 = new VisitorOffset();
		
		Visitor v5 = new ExecuteCGVisitor(args[0],args[1]);
		
		programa.accept(v1, null);
		programa.accept(v2, null);
		programa.accept(v3, null);
		
		programa.accept(v4, null);
		
		programa.accept(v5, null);

		// * Comprobamos si hubo errores
		if (ME.getME().huboErrores()) {
			// * Mostramos los errores
			ME.getME().mostrarErrores(System.err);
		} else {
			// * Mostramos el AST
			IntrospectorModel modelo = new IntrospectorModel("Programa", parser.getAST());
			new IntrospectorTree("Introspector", modelo);
		}
	}

}