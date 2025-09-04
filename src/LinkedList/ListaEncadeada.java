package LinkedList;

public class ListaEncadeada {
    private No prim, ult;
    private int quant;


    public ListaEncadeada(){
        this.prim = this.ult = null;
        this.quant = 0;
    }


    public void inserirElemento(Object elem){
        No novo = new No(elem);
        //Se a lista está vazia
        if (this.prim == null){
            this.prim = novo;
            this.ult = novo;
        }
        //Se não estiver vazia
        else{
            this.ult.setProx(novo);
            this.ult = novo;
        }
        this.quant++;
    }


    public boolean contemElemento(Object elem){
        No aux = this.prim;
        while (aux!=null){
            if (aux.getValor().equals(elem))
                return true;
            aux = aux.getProx();
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
        No aux = this.prim;
        String s = "[";
        while(aux != null){
            s += aux.getValor();
            if (aux != this.ult)
                s+=",";
            aux = aux.getProx();
        }
        return (s+"]");
    }


}
