package parte1;

public class No<T>{
    private T valor;
    private No<T> prox = null;


    public No(T valor){
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public No<T> getProx() {
        return this.prox;
    }

    public void setProx(No<T> novoProx) {
        this.prox = novoProx;
    }
}
