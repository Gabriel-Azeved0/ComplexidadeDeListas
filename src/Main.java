import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        // === ler caminho (stdin ou arg) ===
        String caminho;
        if (args.length >= 1) {
            caminho = args[0];
        } else {
            System.out.println("Digite o caminho do arquivo para o teste:");
            System.out.print("> ");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            caminho = in.readLine();
        }
        if (caminho == null) return;
        caminho = caminho.trim(); // evita espaços ao copiar/colar

        // =============== EDITAR AQUI =================
        Comparator<Aluno> comparador = new ComparadorAlunoPorMatricula();
        // Comparator<Aluno> comparador = new ComparadorAlunoPorNome();

        // Escolha UMA estrutura e comente a outra:
        ListaVetor<Aluno> ds = new ListaVetor<>(comparador, false);
        // ListaEncadeada<Aluno> ds = new ListaEncadeada<>(comparador, /*ordenada=*/true);

        String label = "ListaVetor,ordenada=false";
        // String label = "ListaEncadeada,ordenada=true";
        // =============================================

        final Aluno[] primeiro = new Aluno[1]; // p/ buscar/contém (existente)
        final Aluno[] segundo  = new Aluno[1]; // p/ remover (existente)
        final Aluno inexistente = new Aluno("__NAO_EXISTE__", "Nao Existe");

        System.out.println("Abrindo: " + caminho);
        System.out.println("Inserindo (isso pode levar um tempo)...");
        System.out.flush();

        // 1) adicionar (inserir todos) + progresso
        final AtomicInteger total = new AtomicInteger(0);
        long t0 = System.nanoTime();
        LeitorArquivos.consumir(caminho, a -> {
            if (primeiro[0] == null)      primeiro[0] = a;
            else if (segundo[0] == null)  segundo[0]  = a;

            ds.inserirElemento(a);

            int c = total.incrementAndGet();
            if ((c % 100_000) == 0) {
                System.out.println("  inseridos: " + c);
                System.out.flush();
            }
        });
        long t1 = System.nanoTime();
        double msAdicionar = nanosToMs(t1 - t0);
        System.out.printf("adicionar (%,d itens): %.3f ms%n", total.get(), msAdicionar);

        if (segundo[0] == null) segundo[0] = primeiro[0];

        // 6) contem (existente)
        double msContemExistente = medirMs(() -> ds.contemElemento(primeiro[0]));
        System.out.printf("contem (existente): %.3f ms%n", msContemExistente);

        // 7) contem (inexistente)
        double msContemInexistente = medirMs(() -> ds.contemElemento(inexistente));
        System.out.printf("contem (inexistente): %.3f ms%n", msContemInexistente);

        // 4) pesquisar (existente)
        double msPesquisarExistente = medirMs(() -> ds.pesquisar(primeiro[0]));
        System.out.printf("pesquisar (existente): %.3f ms%n", msPesquisarExistente);

        // 5) pesquisar (inexistente)
        double msPesquisarInexistente = medirMs(() -> ds.pesquisar(inexistente));
        System.out.printf("pesquisar (inexistente): %.3f ms%n", msPesquisarInexistente);

        // 2) remover (existente)
        double msRemoverExistente = medirMs(() -> ds.excluirElemento(segundo[0]));
        System.out.printf("remover (existente): %.3f ms%n", msRemoverExistente);

        // 3) remover (inexistente)
        double msRemoverInexistente = medirMs(() -> ds.excluirElemento(inexistente));
        System.out.printf("remover (inexistente): %.3f ms%n", msRemoverInexistente);

        // CSV minimalista (1 linha por execução, sem cabeçalho)
        try (FileWriter fw = new FileWriter("tempos.csv", true)) {
            fw.write(String.join(",",
                    label,
                    caminho,
                    Integer.toString(total.get()),
                    fmt(msAdicionar),
                    fmt(msRemoverExistente),
                    fmt(msRemoverInexistente),
                    fmt(msPesquisarExistente),
                    fmt(msPesquisarInexistente),
                    fmt(msContemExistente),
                    fmt(msContemInexistente)
            ));
            fw.write("\n");
        }
    }

    // -------- utils ----------
    private static double medirMs(Runnable r) {
        long t0 = System.nanoTime();
        r.run();
        long t1 = System.nanoTime();
        return nanosToMs(t1 - t0);
    }
    private static double nanosToMs(long nanos) { return nanos / 1_000_000.0; }
    private static String fmt(double v) { return String.format(java.util.Locale.US, "%.3f", v); }
}
