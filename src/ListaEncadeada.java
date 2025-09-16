import java.util.Comparator;

public class ListaEncadeada<T extends Comparable<T>> {
    private No<T> prim;
    private No<T> ult;
    private int quant;
    private final Comparator<T> ordenador;
    private final boolean isOrdenada;

    public ListaEncadeada (Comparator<T> ordenador, boolean isOrdenada){
        this.prim = this.ult = null;
        this.quant = 0;
        this.ordenador = ordenador;
        this.isOrdenada = isOrdenada;
    }

    public void inserirElemento(T elem) {
        if (contemElemento(elem)){
            System.out.println("Esse elemento ja existe na lista.");
            return;
        }
        No<T> novo = new No<>(elem);
        if(this.prim == null){
            this.prim = this.ult = novo;
            this.ult.setProx(null);
            quant++;
            System.out.println("Elemento adicionado na lista.");
            return;
        }
        if(isOrdenada) {
            No<T> atual = this.prim;
            No<T> ant = this.prim;
            int result_comp;
            while (atual != null) {//condição que para ao chegar ao fim da lista
                result_comp = ordenador.compare(atual.getValor(), novo.getValor());
                if (result_comp > 0) {
                    if(atual == ant){//caso só tenha um elemento na lista, e precise inserir antes dele
                        this.prim = novo;
                        novo.setProx(atual);
                        quant++;
                        System.out.println("Elemento adicionado na lista.");
                        return;
                    }
                    //ja passou pela posição que estaria o elemento
                    ant.setProx(novo);
                    novo.setProx(atual);
                    quant++;
                    System.out.println("Elemento adicionado na lista.");
                    return;
                }
                ant = atual;
                atual = atual.getProx();
            }
        }
        this.ult.setProx(novo);
        this.ult = novo;
        this.ult.setProx(null);
        quant++;
        System.out.println("Elemento adicionado na lista.");
    }


    public boolean contemElemento(T elem) {
        No<T> novo = new No<>(elem);
        No<T> atual = this.prim;
        int result_comp;

        while (atual != null) {//condição que para ao chegar ao fim da lista
            result_comp = ordenador.compare(atual.getValor(), novo.getValor());
            if (result_comp == 0) {//São iguais
                return true;
            }
            if (result_comp > 0 && isOrdenada) {//caso seja ordenada e ja passou pela posição que estaria o elemento
                return false;
            }
            atual = atual.getProx();
        }
        return false;
    }

    public T pesquisar(T elem){
        No<T> novo = new No<>(elem);
        No<T> atual = this.prim;
        int result_comp;

        if(this.prim == null){
            System.out.println("Elemento nao encontrado, lista vazia");
        }

        while (atual != null) {//condição que para ao chegar ao fim da lista
            result_comp = ordenador.compare(atual.getValor(), novo.getValor());
            if (result_comp == 0) {//São iguais
                return novo.getValor();
            }
            if (result_comp > 0 && isOrdenada) {//caso seja ordenada e ja passou pela posição que estaria o elemento
                return null;
            }
            atual = atual.getProx();
        }
        return null;
    }

    public T excluirElemento(T elem) {
        No<T> novo = new No<>(elem);
        No<T> atual = this.prim;
        No<T> ant = this.prim;

        if(this.prim == null){
            System.out.println("Elemento nao encontrado, lista vazia");
            return null;
        }

        int result_comp;

        while (atual != null) {//condição que para ao chegar ao fim da lista
            result_comp = ordenador.compare(atual.getValor(), novo.getValor());
            if (result_comp == 0) {//São iguais
                ant.setProx(atual.getProx());
                return atual.getValor();
            }
            if (result_comp > 0 && isOrdenada) {//caso seja ordenada e ja passou pela posição que estaria o elemento
                return null;
            }
            ant = atual;
            atual = atual.getProx();
        }
        return null;
    }




    @Override
    public String toString(){
        No<T> aux = this.prim;
        String s = "[";
        while(aux != null){
            s += aux.getValor().toString();
            if (aux != this.ult)
                s+=",";
            aux = aux.getProx();
        }
        return (s+"]");
    }


}
