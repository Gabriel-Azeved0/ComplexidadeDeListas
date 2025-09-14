//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ComparadorAlunoPorMatricula comparador = new ComparadorAlunoPorMatricula();
        ListaEncadeada<Aluno> l = new ListaEncadeada<Aluno>(comparador, true);
        Aluno a = new Aluno("24", "Gabriel");
        Aluno b = new Aluno("22", "Amanda");
        Aluno c = new Aluno("1", "Hiago");
        Aluno h = new Aluno("15", "Pedro");
        Aluno j = new Aluno("22", "Carlos");
        Aluno i = new Aluno("33", "Jorge");
        l.inserirElemento(a);
        l.inserirElemento(b);
        l.inserirElemento(c);
        l.inserirElemento(h);
        l.inserirElemento(j);
        l.inserirElemento(i);
        System.out.println(l);
        Aluno d = new Aluno("24", "Amanda");
        Aluno f = new Aluno("23", "Gabriel");
        System.out.println(l.contemElemento(d));
        System.out.println(l.contemElemento(f));
    }
}