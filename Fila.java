package calculadoraExpPosFixa;

import javax.swing.JOptionPane;

public class Fila {
    int inicio;
    int fim;
    int tamanho;
    int qtdeElementos;
    int f[];

    public Fila(){
        inicio = fim = -1;
        tamanho = 200;
        f = new int[tamanho];
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
            if (inicio == -1){
                inicio = 0;
            }
            fim++;
            f[fim] = e;
            qtdeElementos++;
        }
    }

    public void remover(){
        if (! estaVazia() ){
            inicio++;
            qtdeElementos--;
        }
        for(int n = 0; n < qtdeElementos; n++){
            f[n] = f[inicio];
            inicio++;
        }
        fim--;
        inicio = 0;
    }

    public void mostrar(){
        String elementos = "";
            for (int i = inicio; i<=fim; i++) {
                elementos += f[i]+ " ";
            }
            //JOptionPane.showMessageDialog(null, elementos);
            System.out.println(elementos);
    }
/*    public static void main(String[] args) {
        Fila f = new Fila();   
        f.adicionar(10);
        f.adicionar(12);
        f.adicionar(30);
        f.mostrar();
        f.remover();
        f.mostrar();
    }                                             */
}