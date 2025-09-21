public class Main {
    public static void main(String[] args) throws Exception {
        ComparadorAlunoPorMatricula compM = new ComparadorAlunoPorMatricula();
        ComparadorAlunoPorNome compN = new ComparadorAlunoPorNome();

        ListaEncadeada nome_ord = new ListaEncadeada<>(compN, true);
        ListaEncadeada nome_desord = new ListaEncadeada<>(compN, false);
        ListaEncadeada mat_ord = new ListaEncadeada<>(compM, true);
        ListaEncadeada mat_desord = new ListaEncadeada<>(compM, false);

        Aluno a = new Aluno("1", "Pedro");
        Aluno b = new Aluno("2", "Gabriel");
        Aluno c = new Aluno("3", "Rafael");
        Aluno d = new Aluno("10", "Jorge");
        Aluno e = new Aluno("11", "Raissa");
        Aluno f = new Aluno("4", "Amanda");
        Aluno g = new Aluno("5", "Flavio");
        Aluno h = new Aluno("12", "Marcia");

        //Testes Lista Ordenada comparator Nome:
        nome_ord.inserirElemento(a);
        nome_ord.inserirElemento(b);
        nome_ord.inserirElemento(c);
        nome_ord.inserirElemento(d);
        nome_ord.inserirElemento(e);
        nome_ord.inserirElemento(f);
        nome_ord.inserirElemento(g);
        System.out.println("Lista Ordenada por Nome: " + nome_ord);

        System.out.println(nome_ord.contemElemento(g));
        System.out.println(nome_ord.contemElemento(h));

        System.out.println(nome_ord.pesquisar(a));
        System.out.println(nome_ord.pesquisar(h));

        System.out.println(nome_ord.excluirElemento(b));
        System.out.println(nome_ord.excluirElemento(h));

        System.out.println("Lista Ordenada comparator Nome: " + nome_ord);

        //Testes Lista Desordenada comparator Nome:
        nome_desord.inserirElemento(a);
        nome_desord.inserirElemento(b);
        nome_desord.inserirElemento(c);
        nome_desord.inserirElemento(d);
        nome_desord.inserirElemento(e);
        nome_desord.inserirElemento(f);
        nome_desord.inserirElemento(g);
        System.out.println("Lista Nao ordenada comparator Nome: " + nome_desord);

        System.out.println(nome_desord.contemElemento(g));
        System.out.println(nome_desord.contemElemento(h));

        System.out.println(nome_desord.pesquisar(a));
        System.out.println(nome_desord.pesquisar(h));

        System.out.println(nome_desord.excluirElemento(b));
        System.out.println(nome_desord.excluirElemento(h));

        System.out.println("Lista Nao ordenada comparator Nome: " + nome_desord);

        //Testes Lista Ordenada comparator Matricula:
        mat_ord.inserirElemento(a);
        mat_ord.inserirElemento(b);
        mat_ord.inserirElemento(c);
        mat_ord.inserirElemento(d);
        mat_ord.inserirElemento(e);
        mat_ord.inserirElemento(f);
        mat_ord.inserirElemento(g);
        System.out.println("Lista Ordenada comparator Matricula: " + mat_ord);

        System.out.println(mat_ord.contemElemento(g));
        System.out.println(mat_ord.contemElemento(h));

        System.out.println(mat_ord.pesquisar(a));
        System.out.println(mat_ord.pesquisar(h));

        System.out.println(mat_ord.excluirElemento(b));
        System.out.println(mat_ord.excluirElemento(h));

        System.out.println("Lista Ordenada comparator Matricula: " + mat_ord);

        //Testes Lista Desordenada comparator Matricula:
        mat_desord.inserirElemento(a);
        mat_desord.inserirElemento(b);
        mat_desord.inserirElemento(c);
        mat_desord.inserirElemento(d);
        mat_desord.inserirElemento(e);
        mat_desord.inserirElemento(f);
        mat_desord.inserirElemento(g);
        System.out.println("Lista Nao ordenada comparator Matricula: " + mat_desord);

        System.out.println(mat_desord.contemElemento(g));
        System.out.println(mat_desord.contemElemento(h));

        System.out.println(mat_desord.pesquisar(a));
        System.out.println(mat_desord.pesquisar(h));

        System.out.println(mat_desord.excluirElemento(b));
        System.out.println(mat_desord.excluirElemento(h));

        System.out.println("Lista Nao ordenada comparator Matricula: " + mat_desord);
    }
}
