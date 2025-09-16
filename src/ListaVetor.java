import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class ListaVetor<T extends Comparable<T>> {
    private Object[] vetor;
    private int quant;
    private int tamVetor = 50;
    private final Comparator<T> ordenador;
    private final boolean isOrdenada;

    public ListaVetor(Comparator<T> ordenador, boolean isOrdenada) {
        this.vetor = new Object[tamVetor];
        this.quant = 0;
        this.ordenador = ordenador;
        this.isOrdenada = isOrdenada;
    }

    public void inserirElemento(T novoValor) {
        if (!this.contemElemento(novoValor)) {
            if (!this.isOrdenada) {
                if (this.tamVetor == this.quant) {
                    this.tamVetor *= 2;
                    Object[] novoVetor = new Object[this.tamVetor];
                    int posiNovoVetor = 0;
                    for (Object elemento : this.vetor) {
                        if (elemento != null) {
                            novoVetor[posiNovoVetor] = elemento;
                            posiNovoVetor++;
                        }
                    }
                    this.vetor = novoVetor;
                }
                vetor[this.quant] = novoValor;
                this.quant++;
            } else {
                int posInsercao = this.buscarPosicaoInsercao(novoValor, this.quant);
                if (posInsercao < 0) {
                    System.out.println("Elemento ja existe na lista.");
                    return;
                }
                int qtdMovidos = this.quant - posInsercao;
                if (qtdMovidos > 0) {
                    if (this.tamVetor == this.quant) {
                        this.tamVetor *= 2;
                        Object[] novoVetor = new Object[this.tamVetor];
                        int posiNovoVetor = 0;
                        for (Object elemento : this.vetor) {
                            if (this.buscarPosicaoInsercao((T) elemento, this.quant) == posInsercao) {
                                novoVetor[posiNovoVetor] = novoValor;
                                posiNovoVetor++;
                                this.quant++;
                            }
                            novoVetor[posiNovoVetor] = elemento;
                        }
                        this.vetor = novoVetor;
                    }
                }
            }
        }
    }

    public boolean contemElemento(T valor) {
        if(this.pesquisar(valor) == null){
            return false;
        }
        return true;
    }


    public T pesquisar(T valor){
        if(this.isOrdenada) {
            int posiValor = this.buscarPosicaoInsercao(valor, this.quant);
            int resultComp = this.ordenador.compare(this.obter(posiValor), valor);
            if (resultComp == 0)
                return this.obter(posiValor);
        }
        else{
            T atual;
            for (int i=0; i<this.quant;i++){
                atual = obter(i);
                int resultComp = this.ordenador.compare(atual, valor);
                if (resultComp == 0)
                    return atual;
            }
        }
        return null;
    }

    public T excluirElemento(T valor){//ideia, trocar por null o elemento a ser excluido, copiar a lista para a nova lista ignorando o elemento null, e entao transformar a velha lista na nova
        T valorRemovido = null;
        if(!this.contemElemento(valor)){
            return valorRemovido;
        }
        else {
            if (this.isOrdenada) {//caso seja ordenada
                int posiValor = buscarPosicaoInsercao(valor, this.quant);
                if (posiValor < 0) {
                    return valorRemovido;
                }
                valorRemovido = this.obter(posiValor);
                this.vetor[posiValor] = null;
            }
            else {//caso seja desordenada
                for (int i = 0; i < this.quant; i++) {
                    if (this.vetor[i] == valor) {
                        valorRemovido = obter(i);
                        this.vetor[i] = null;
                        break;
                    }
                }
            }
            //cria novo vetor, preenche com os valores do vetor original ignorando o elem null
            Object[] novoVetor = new Object[this.tamVetor];
            int posiNovoVetor = 0;
            for (Object elemento : this.vetor) {
                if (elemento != null) {
                    novoVetor[posiNovoVetor] = elemento;
                    posiNovoVetor++;
                }
            }
            //atualiza quantidade e o vetor agora com o elemento removido
            this.quant--;
            this.vetor = novoVetor;
        }
        return valorRemovido;
    }

    private int buscarPosicaoInsercao(T valor, int fim) {
        int esquerda = 0;
        int direita = fim;
        int result_comp;
        T atual;
        while (esquerda < direita) {
            int meio = esquerda + ((direita - esquerda) >>> 1);
            atual = obter(meio);
            result_comp = this.ordenador.compare(atual, valor);
            if (result_comp < 0) {
                esquerda = meio + 1;   // atual < valor -> procurar à direita
            } else {
                direita = meio;        // atual >= valor -> estreita à esquerda
            }
        }
        return esquerda; // ponto de inserção
    }

    private T obter(int i) {
        //preciso do cast pois o vetor[i] retorna um object, e meu comparable usa tipo T generico
        return (T) this.vetor[i];
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("[");
        for(int i=0; i<this.quant; i++){
            s.append(this.vetor[i].toString());
            s.append(",");
        }
        return (s+"]");
    }

    public int getQuant() {
        return this.quant;
    }

}
