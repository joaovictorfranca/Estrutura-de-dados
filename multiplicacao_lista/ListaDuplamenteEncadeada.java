package multiplicacao_lista;

public class ListaDuplamenteEncadeada {
    No inicio = null;
    No fim = null;
    int qntElementos = 0;

    public void inserirInicio (String informacao) {
        No noAtual = new No();
        noAtual.dado = informacao;
        noAtual.noAnterior = null;
        noAtual.noPosterior = inicio;
        if (inicio != null) {
            inicio.noAnterior = noAtual;
        }
        inicio = noAtual;
        if (qntElementos == 0) {
            fim = inicio;
        }
        qntElementos = qntElementos + 1;
    }

    public String removerInicio() {
        if(inicio == null) {
            return ("Sem elementos para remover!");
        }
        String elementoRemovido = inicio.dado;
        inicio = inicio.noPosterior;
        if (inicio != null) {
            inicio.noAnterior = null;
        } else {
            fim = null;
        }
        qntElementos = qntElementos - 1;
        return elementoRemovido;
    }

    public void inserirFinal (String informacao) {
        No noAtual = new No();
        noAtual.dado = informacao;
        noAtual.noPosterior = null;
        noAtual.noAnterior = fim;
        if (fim != null) {
            fim.noPosterior = noAtual;
        }
        fim = noAtual;
        if (qntElementos == 0) {
            inicio = fim;
        }
        qntElementos = qntElementos + 1;
    }

    public String removerFim() {
        if (fim == null) {
            return ("Sem elemntos para remover!");
        }
        String elementoRemovido = fim.dado;
        fim = fim.noAnterior;
        if (fim != null) {
            fim.noPosterior = null;
        } else {
            inicio = null;
        }
        qntElementos = qntElementos -1;
        return elementoRemovido;
    }

    public void inserirLocal(int indice , String informacao) {
        if (indice <= 0) {
            inserirInicio(informacao);
        } else if (indice >= qntElementos) {
            inserirFinal(informacao);
        }
        No ponteiroLocal = inicio;
        for (int i = 0; i < indice-1; i++) {
            ponteiroLocal = ponteiroLocal.noPosterior;
        }
        No noAtual = new No();
        noAtual.dado = informacao;
        noAtual.noAnterior = ponteiroLocal;
        noAtual.noPosterior = ponteiroLocal.noPosterior;
        ponteiroLocal.noPosterior = noAtual;
        noAtual.noPosterior.noAnterior = noAtual;
        qntElementos = qntElementos +1;
        
    }
    
    public String removerLocal(int indice) {
        if (indice < 0 || indice >= qntElementos || inicio == null) {
            return ("Não existe elemento nessa posição!");
        } else if (indice == 0) {
            return removerInicio();
        } else if (indice == qntElementos-1) {
            return removerFim();
        }
        No ponteiroLocal = inicio;
        for (int i = 0; i < indice-1; i++) {
            ponteiroLocal = ponteiroLocal.noPosterior;
        }
        if (ponteiroLocal.noAnterior != null) {
            ponteiroLocal.noAnterior.noPosterior = ponteiroLocal.noPosterior;
        }
        if (ponteiroLocal.noPosterior != null) {
            ponteiroLocal.noPosterior.noAnterior = ponteiroLocal.noAnterior;
        }
        qntElementos = qntElementos +1;
        return ponteiroLocal.dado;
    }

    public String toString() {
        No count = inicio;
        String lista = "";
        if (count == null) {
            return ("Sem elementos!");
        } else {
            while(count != null) {
                lista = lista + count.dado + " ";
                count = count.noPosterior;
            }
        }
        return lista;
    }
}