import java.util.Comparator;
import java.util.Objects;

public class ListaVetor<T> {
    private T[] a;
    private int n;
    private final boolean ordenada;
    private final Comparator<T> cmp;
    //"private final Comparator<? super T> cmp; - refinamento"

    public ListaVetor(boolean ordenada, Comparator<T> comparator) {
        this.ordenada = ordenada;
        this.cmp = Objects.requireNonNull(comparator, "Comparator não pode ser null");
        this.a = (T[]) new Object[16];
        this.n = 0;
    }

    public int tamanho() { return n; }
    public boolean vazia() { return n == 0; }
    public boolean isOrdenada() { return ordenada; }

    public void adicionar(T v) {
        garantirCap(n + 1);
        if (!ordenada) {
            a[n++] = v;                   // O(1) amortizado
            return;                       // lembrar de conferir se a lista ja esta cheia, se estiver instanciar uma maior e depois adicionar um elemento ao final
        }
        int pos = posicaoInsercao(v);     // O(log n)
        shiftDireita(pos);                 // O(n)
        a[pos] = v;
        n++;
    }

    public boolean contemElemento(T v) {
        return ordenada ? indiceBin(v) >= 0 : indiceLinear(v) >= 0;
    }

    public T pesquisar(T v) {
        int i = ordenada ? indiceBin(v) : indiceLinear(v);
        return (i >= 0) ? a[i] : null;
    }

    public T remover(T v) {
        int i = ordenada ? indiceBin(v) : indiceLinear(v);
        if (i < 0) return null;
        T out = a[i];
        shiftEsquerda(i);                 // O(n) no pior caso
        n--;
        a[n] = null;
        return out;
    }

    private void garantirCap(int nec) {
        if (nec <= a.length) return;
        int novaCap = Math.max(a.length * 2, nec);
        T[] b = (T[]) new Object[novaCap];
        System.arraycopy(a, 0, b, 0, n);  // O(n)
        a = b;
    }

    private void shiftDireita(int pos) {
        System.arraycopy(a, pos, a, pos + 1, n - pos);
    }

    private void shiftEsquerda(int pos) {
        int qtd = n - pos - 1;
        if (qtd > 0) System.arraycopy(a, pos + 1, a, pos, qtd);
    }

    private int indiceBin(T v) {
        int l = 0, r = n - 1;
        while (l <= r) {
            int m = (l + r) >>> 1;
            int c = cmp.compare(a[m], v);
            if (c == 0) return m;
            if (c < 0) l = m + 1; else r = m - 1;
        }
        return -(l) - 1;
    }

    private int posicaoInsercao(T v) {
        int k = indiceBin(v);
        if (k >= 0) {
            int pos = k + 1;
            while (pos < n && cmp.compare(a[pos], v) == 0) pos++;
            return pos;
        }
        return -k - 1;
    }

    private int indiceLinear(T v) {
        for (int i = 0; i < n; i++) {
            if (cmp.compare(a[i], v) == 0) return i;
        }
        return -1;
    }
}
