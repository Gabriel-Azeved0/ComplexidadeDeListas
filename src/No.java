public class No<T>{
    private T valor;
    private No prox;


    public No<T>(T valor){
        this.valor = valor;
        this.prox = null;
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
