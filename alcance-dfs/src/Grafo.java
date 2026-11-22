import java.util.*;
public class Grafo<T> {

    public HashMap<T, LinkedList<Aresta<T>>> meuGrafo;
    ArrayList<T> visitados;

    public Grafo() {
        this.meuGrafo = new HashMap<>();
    }

    public boolean adicionarVertice(T vertice) {
        if (!meuGrafo.containsKey(vertice)) {
            meuGrafo.put(vertice, new LinkedList<>());
            return true;
        }
        return false;
    }

    public boolean adicionarAresta(T origem, T destino, int peso, boolean direcionado) {
        if (meuGrafo.containsKey(origem) && meuGrafo.containsKey(destino)) {
            meuGrafo.get(origem).add(new Aresta<>(destino, peso));
            if (!direcionado) {
                meuGrafo.get(destino).add(new Aresta<>(origem, peso));
            }
            return true;
        }
        return false;
    }

    public void mostrarGrafo() {
        for (Map.Entry<T, LinkedList<Aresta<T>>> registro : meuGrafo.entrySet()) {
            T vertice = registro.getKey();
            LinkedList<Aresta<T>> arestas = registro.getValue();
            System.out.print("Vértice " + vertice + ": ");
            for (Aresta<T> aresta : arestas) {
                System.out.print("-> (Destino: " + aresta.vertice + ", Peso: " + aresta.peso + ") ");
            }
            System.out.println("\n");
        }
    }

    public boolean alcance(T origem, T destino) {
        if (origem == null || destino == null
                || !meuGrafo.containsKey(origem) || !meuGrafo.containsKey(destino)) {
            return false;
        }
        ArrayList<T> visitados = new ArrayList<>();
        return existeCaminho(origem, destino, visitados);
    }

    private boolean existeCaminho(T verticeAtual, T destino, ArrayList<T> visitados) {
        if (verticeAtual.equals(destino)) {
            return true;
        }
        visitados.add(verticeAtual);
        for (Aresta<T> aresta : meuGrafo.get(verticeAtual)) {
            if (!visitados.contains(aresta.vertice)
                    && existeCaminho(aresta.vertice, destino, visitados)) {
                return true;
            }
        }
        return false;
    }
}
