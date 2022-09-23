package calculadoraexposfixa;


public class Fila {
    Integer inicio;
	Integer fim;
    int tamanho;
    int qtdeElementos;
    int fila[];

    public Fila(){
        inicio = fim = null;
        tamanho = 200;
        fila = new int[tamanho];
        qtdeElementos = 0;
    }

    public boolean estaVazia(){
        if (qtdeElementos == 0){
            return true;
        }
        return false;
    }

    public boolean estaCheia(){
        if (qtdeElementos == tamanho - 1){
            return true;
        }
        return false;
    }

    public void adicionar(int e){
        if (! estaCheia()){
            if (inicio == null || fim == null) {
				inicio = 0;
				fim = 0;
				fila[fim] = e;
				qtdeElementos++;
			} else {
				fim++;
				fila[fim] = e;
				qtdeElementos++;
			}
        }
    }

    public void remover(){
        if (! estaVazia() ){
            if(qtdeElementos == 1) {
				fila[inicio] = 0;
				inicio = null;
				fim = null;
				qtdeElementos--;
			} else {
				fila[inicio] = 0;
				inicio = 0;
                fim--;
				qtdeElementos--;
			}
        }
        for(int n = 0; n < qtdeElementos; n++){
            if(fila[n+1] != 0){
                fila[n] = fila[n+1];
            }else{
                break;
            }
        }
    }

    public void mostrar(){
        String elementos = "";
            for (int i = inicio; i<=fim; i++) {
                elementos += fila[i]+ " ";
            }
            System.out.println(elementos);
    }
}