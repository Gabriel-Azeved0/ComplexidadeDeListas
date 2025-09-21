package parte4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static volatile Object SINK;

    static class Aluno {
        int matricula;
        String nome;
        Aluno(int m, String n) { this.matricula = m; this.nome = n; }
        @Override public String toString() { return matricula + ";" + nome; }
    }

    public static void main(String[] args) throws Exception {
        // Passar caminho do arquivo txt
        String caminho = "C:\\Users\\bield\\IdeaProjects\\ComplexidadeDeListas\\alunosBalanceados.txt";

        // Leitura de linha a linha do txt armazenando numa lista Alunos
        List<Aluno> base = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        br.readLine();
        String linha;
        int count = 0;
        while ((linha = br.readLine()) != null) {
            if (linha.isBlank()) continue;
            String[] p = linha.split(";", 3);
            int mat = Integer.parseInt(p[0].trim());
            String nome = p[1].trim();
            base.add(new Aluno(mat, nome));

            count++;
            if (count % 50_000 == 0) {
                System.out.println("Adicionados: " + count + " elementos...");
            }
        }
        br.close();

        int n = base.size();
        PrintWriter csv = new PrintWriter("resultados_listas.csv");
        csv.println("estrutura,operacao,tamanho_inicial,indice,tempo_ns,tempo_ms");

        // ====================== ArrayList ======================
        {
            // inserir no fim
            List<Aluno> l = new ArrayList<>(base);
            Aluno novo = new Aluno(999999999, "Novo Fim");
            int idx = l.size();
            long t0 = System.nanoTime();
            l.add(novo);
            long t1 = System.nanoTime();
            csv.println("ArrayList,inserir_fim," + n + "," + idx + "," + (t1 - t0) + "," + ((t1 - t0) / 1_000_000.0));

            // inserir no começo
            l = new ArrayList<>(base);
            novo = new Aluno(888888888, "Novo Comeco");
            idx = 0;
            t0 = System.nanoTime();
            l.add(0, novo);
            t1 = System.nanoTime();
            csv.println("ArrayList,inserir_comeco," + n + "," + idx + "," + (t1 - t0) + "," + ((t1 - t0) / 1_000_000.0));

            // inserir no meio
            l = new ArrayList<>(base);
            novo = new Aluno(777777777, "Novo Meio");
            idx = l.size() / 2;
            t0 = System.nanoTime();
            l.add(idx, novo);
            t1 = System.nanoTime();
            csv.println("ArrayList,inserir_meio," + n + "," + idx + "," + (t1 - t0) + "," + ((t1 - t0) / 1_000_000.0));

            // buscar último
            l = new ArrayList<>(base);
            idx = l.size() - 1;
            t0 = System.nanoTime();
            SINK = l.get(idx);
            t1 = System.nanoTime();
            csv.println("ArrayList,buscar_ultimo," + n + "," + idx + "," + (t1 - t0) + "," + ((t1 - t0) / 1_000_000.0));

            // buscar penúltimo
            l = new ArrayList<>(base);
            idx = l.size() - 2;
            t0 = System.nanoTime();
            SINK = l.get(idx);
            t1 = System.nanoTime();
            csv.println("ArrayList,buscar_penultimo," + n + "," + idx + "," + (t1 - t0) + "," + ((t1 - t0) / 1_000_000.0));

            // buscar meio
            l = new ArrayList<>(base);
            idx = l.size() / 2;
            t0 = System.nanoTime();
            SINK = l.get(idx);
            t1 = System.nanoTime();
            csv.println("ArrayList,buscar_meio," + n + "," + idx + "," + (t1 - t0) + "," + ((t1 - t0) / 1_000_000.0));
        }

        // ====================== LinkedList ======================
        {
            List<Aluno> l;
            Aluno novo;
            int idx;
            long t0, t1;

            // inserir no fim
            l = new LinkedList<>(base);
            novo = new Aluno(999999999, "Novo Fim");
            idx = l.size();
            t0 = System.nanoTime();
            l.add(novo);
            t1 = System.nanoTime();
            csv.println("LinkedList,inserir_fim," + n + "," + idx + "," + (t1 - t0) + "," + ((t1 - t0) / 1_000_000.0));

            // inserir no começo
            l = new LinkedList<>(base);
            novo = new Aluno(888888888, "Novo Comeco");
            idx = 0;
            t0 = System.nanoTime();
            l.add(0, novo);
            t1 = System.nanoTime();
            csv.println("LinkedList,inserir_comeco," + n + "," + idx + "," + (t1 - t0) + "," + ((t1 - t0) / 1_000_000.0));

            // inserir no meio
            l = new LinkedList<>(base);
            novo = new Aluno(777777777, "Novo Meio");
            idx = l.size() / 2;
            t0 = System.nanoTime();
            l.add(idx, novo);
            t1 = System.nanoTime();
            csv.println("LinkedList,inserir_meio," + n + "," + idx + "," + (t1 - t0) + "," + ((t1 - t0) / 1_000_000.0));

            // buscar último
            l = new LinkedList<>(base);
            idx = l.size() - 1;
            t0 = System.nanoTime();
            SINK = l.get(idx);
            t1 = System.nanoTime();
            csv.println("LinkedList,buscar_ultimo," + n + "," + idx + "," + (t1 - t0) + "," + ((t1 - t0) / 1_000_000.0));

            // buscar penúltimo
            l = new LinkedList<>(base);
            idx = l.size() - 2;
            t0 = System.nanoTime();
            SINK = l.get(idx);
            t1 = System.nanoTime();
            csv.println("LinkedList,buscar_penultimo," + n + "," + idx + "," + (t1 - t0) + "," + ((t1 - t0) / 1_000_000.0));

            // buscar meio
            l = new LinkedList<>(base);
            idx = l.size() / 2;
            t0 = System.nanoTime();
            SINK = l.get(idx);
            t1 = System.nanoTime();
            csv.println("LinkedList,buscar_meio," + n + "," + idx + "," + (t1 - t0) + "," + ((t1 - t0) / 1_000_000.0));
        }

        csv.flush();
        csv.close();
        System.out.println("resultados_listas.csv gerado.");
    }
}
