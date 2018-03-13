package tablasimbolos;

import java.util.*;
import ast.definicion.*;

public class TablaSimbolos {

	private int ambito = 0;
	private List<Map<String, Definicion>> tabla;

	public TablaSimbolos() {
		tabla = new ArrayList<Map<String, Definicion>>();
		tabla.add(new HashMap<String, Definicion>());
	}

	public void set() {
		tabla.add(new HashMap<String, Definicion>());
		ambito++;
	}

	public void reset() {
		tabla.remove(ambito);
		ambito--;
	}

	public boolean insertar(Definicion simbolo) {
		if (buscarAmbitoActual(simbolo.getNombre()) != null)
			return false;
		tabla.get(ambito).put(simbolo.getNombre(), simbolo);
		if (simbolo instanceof DefVariable)
			((DefVariable) simbolo).setAmbito(ambito);
		return true;
	}

	public Definicion buscar(String id) {
		for (int i = tabla.size() - 1; i >= 0; i--) {
			Map<String, Definicion> amb = tabla.get(i);
			Definicion def = amb.get(id);
			if (def != null)
				return def;
		}
		return null;
	}

	public Definicion buscarAmbitoActual(String id) {
		return tabla.get(ambito).get(id);
	}
}