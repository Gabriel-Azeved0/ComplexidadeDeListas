public class Aluno implements Comparable<Aluno> {

    private String matricula;
    private String nome;

    public Aluno(){

    }

    public Aluno(String m) {
        this.matricula = m;
    }


    @Override
    public int compareTo(String m) {
        //Compara o nome dos alunos
        return this.nome.compareTo(m);
    }

    @Override
}
