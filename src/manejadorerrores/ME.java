package manejadorerrores;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import ast.tipo.TipoError;

public class ME {

	private static ME instance = new ME();
	private List<TipoError> errores;

	private ME() {
		errores = new ArrayList<TipoError>();
	}
	
	public static ME getME() {
		return instance;
	}

	public boolean huboErrores() {
		return !errores.isEmpty();
	}

	public void añadirErrores(TipoError error) {
		errores.add(error);
	}

	public void mostrarErrores(PrintStream err) {
		for (TipoError te : errores)
			err.println(te);
	}

	public List<TipoError> getErrores() {
		return errores;
	}
}	