package multiplicacao_lista;

import java.util.ArrayList;
import java.util.List;

public class ListaDeListas {
	
	List<String> lista = new ArrayList<String>();

	public ListaDeListas() {
		
	}

	public ListaDeListas(List<String> lista) {
		super();
		this.lista = lista;
	}

	public List<String> getLista() {
		return lista;
	}

	public void addLista(List<String> lista) {
		this.lista.addAll(lista);
	}
	
}
