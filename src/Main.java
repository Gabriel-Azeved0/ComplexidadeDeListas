//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ComparadorAlunoPorMatricula comparador = new ComparadorAlunoPorMatricula();
        ListaVetor<Aluno> v = new ListaVetor<Aluno>(comparador, false);
        ListaEncadeada<Aluno> l = new ListaEncadeada<Aluno>(comparador, true);
        Aluno a = new Aluno("24", "Gabriel");
        Aluno b = new Aluno("22", "Amanda");
        Aluno c = new Aluno("1", "Hiago");
        Aluno h = new Aluno("15", "Gabriel");
        Aluno j = new Aluno("22", "Carlos");
        Aluno i = new Aluno("33", "Jorge");

        //Testando inserir elemento lista encadeada
        v.inserirElemento(a);
        System.out.println(v);
        v.inserirElemento(b);
        System.out.println(v);
        v.inserirElemento(c);
        System.out.println(v);
        v.inserirElemento(h);
        System.out.println(v);
        v.inserirElemento(j);
        System.out.println(v);
        v.inserirElemento(i);
        System.out.println(v);

        System.out.println(v.contemElemento(a));
        System.out.println(v.contemElemento(j));
        System.out.println(v.pesquisar(j));
        System.out.println(v.getQuant());
        System.out.println(v.remover(a));
        System.out.println(v.remover(c));
        System.out.println(v.getQuant());
        System.out.println(v);

        /*
        //Testando inserir elemento lista encadeada
        l.inserirElemento(a);
        l.inserirElemento(b);
        l.inserirElemento(c);
        l.inserirElemento(h);
        l.inserirElemento(j);
        l.inserirElemento(i);
        System.out.println(l);



        Aluno d = new Aluno("24", "Amanda");
        Aluno f = new Aluno("23", "Gabriel");

        //Testando contem elemento
        System.out.println(l.contemElemento(d));
        System.out.println(l.contemElemento(f));

        //Testando excluir elemento
        System.out.println(l.excluirELemento(f));
        System.out.println(l.excluirELemento(d));

        System.out.println(l.contemElemento(d));
        //System.out.println(l.excluirELemento());

        */
    }
}