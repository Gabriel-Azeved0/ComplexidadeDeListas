import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class LeitorArquivos {

    /** Lê tudo e devolve uma lista (opção simples para testes). */
    public static List<Aluno> ler(String caminho, int limite) throws IOException {
        List<Aluno> out = new ArrayList<>();
        consumir(caminho, a -> {
            if (limite <= 0 || out.size() < limite) out.add(a);
        });
        return out;
    }

    /** Versão em streaming: chama o consumidor para cada Aluno lido. */
    public static void consumir(String caminho, Consumer<Aluno> consumidor) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(caminho)), StandardCharsets.UTF_8))) {

            // primeira linha = quantidade (pode usar para reservar capacidade, se quiser)
            String first = br.readLine(); // pode ser null se arquivo vazio
            // int total = (first != null && !first.isBlank()) ? Integer.parseInt(first.trim()) : 0;

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;

                // seu formato: id;nome sobrenome;   (repare no ';' final)
                String[] partes = line.split(";", -1); // -1 preserva campo final vazio
                if (partes.length < 2) continue;

                String idStr   = partes[0].trim();
                String nomeStr = partes[1].trim();

                consumidor.accept(new Aluno(idStr, nomeStr));
            }
        }
    }
}
