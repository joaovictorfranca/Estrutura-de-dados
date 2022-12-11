package exp_pos_fixa;


public class Pilha {

	Integer inicio;
	Integer fim;
	int tamanho;
	int qtdeElementos;
	double pilha[];
	
	public Pilha() {
		inicio = fim = null;
		tamanho = 200;
		pilha = new double[tamanho];
		qtdeElementos = 0;
	}

	public boolean estaVazia() {
		if (qtdeElementos == 0) {
			return true;
		}
		return false;
	}

	public boolean estaCheia() {
		if (qtdeElementos == tamanho - 1) {
			return true;
		}
		return false;
	}

	public void push(double a) {
		if (!estaCheia()) {
			if (inicio == null || fim == null) {
				inicio = 0;
				fim = 0;
				pilha[fim] = a;
				qtdeElementos++;
			} else {
				fim++;
				pilha[fim] = a;
				qtdeElementos++;
			}
		}
	}

	public double pop() {
		double item = pilha[fim];
		if (!estaVazia()) {
			if(qtdeElementos == 1) {
				pilha[fim] = 0;
				inicio = null;
				fim = null;
				qtdeElementos--;
			} else {
				pilha[fim] = 0;
				fim--;
				qtdeElementos--;
			}
		}
		return item;
	}

	public void mostrar() {
		String elementos = "";
		for (int i = inicio; i <= fim; i++) {
			elementos += pilha[i] + " ";
		}
		System.out.println(elementos);
	}
}