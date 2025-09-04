public class Aluno implements Comparable<Aluno> {

    private String matricula;
    private String nome;

    public Aluno(){

    }

    public Aluno(String m) {
        this.matricula = m;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
