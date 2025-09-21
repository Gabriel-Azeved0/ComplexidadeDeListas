import java.util.Comparator;

public class ComparadorAlunoPorMatricula implements Comparator<Aluno> {

    @Override
    public int compare(Aluno a1, Aluno a2){
        int matricula_a1 = Integer.parseInt(a1.getMatricula());
        int matricula_a2 = Integer.parseInt(a2.getMatricula());
        return Integer.compare(matricula_a1, matricula_a2);
    }
}
