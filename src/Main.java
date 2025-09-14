//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ComparadorAlunoPorMatricula comparador = new ComparadorAlunoPorMatricula();
        ListaEncadeada l = new ListaEncadeada(comparador, true);
        Aluno a = new Aluno("24", "Gabriel");
        Aluno b = new Aluno("22", "Amanda");
        Aluno c = new Aluno("1", "Hiago");
        l.inserirElemento(a);
        l.inserirElemento(b);
        l.inserirElemento(c);
        System.out.println(l);

    }
}