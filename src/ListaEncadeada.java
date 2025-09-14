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
        }
        if(isOrdenada) {
            No<T> atual = this.prim;
            No<T> ant = this.prim;
            int result_comp;
            while (atual != null) {//condição que para ao chegar ao fim da lista
                result_comp = ordenador.compare(atual.getValor(), novo.getValor());
                if (result_comp > 0 && isOrdenada) {
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


    public boolean excluirELemento(Object elem){
        No aux = this.prim;
        No ant = null;
        while (aux!=null){
            //Se encontrar remove elemento
            if (aux.getValor().equals(elem)){
                //Se for o primeiro elemento, prim passa a apontar para o próximo
                if(aux==this.prim){
                    this.prim = this.prim.getProx();
                    //Verifica se tambem é o ultimo. ou seja, é o unico elemento da lista
                    this.ult = null;
                }
                //Se não é o primeiro, o anterior passa a apontar para o próximo
                else{
                    ant.setProx(aux.getProx());
                    //Se ele for o ult, o ult passa a ser o ant
                    if(aux==this.ult)
                        this.ult = ant;
                }
                //Decremento na quantidade
                this.quant--;
                return true;
            }
            //Se não encontrou, ant vai para aux, e aux vai para o próximo
            ant = aux;
            aux = aux.getProx();
        }
        //Se rodou tudo sem encontrar retorna falso
        return false;
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
