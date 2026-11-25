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
    public List<T> buscaEmProfundidade(T verticeInicial) {
        visitados = new ArrayList<>();
        if (verticeInicial != null && meuGrafo.containsKey(verticeInicial)) {
            explorarDFS(verticeInicial, visitados);
        }
        return visitados;
    }

    private void explorarDFS(T verticeAtual, ArrayList<T> visitados) {
        visitados.add(verticeAtual);
        LinkedList<Aresta<T>> adjacencias = meuGrafo.get(verticeAtual);
        if (adjacencias != null) {
            for (Aresta<T> aresta : adjacencias) {
                if (!visitados.contains(aresta.vertice)) {
                    explorarDFS(aresta.vertice, visitados);
                }
            }
        }
    }

    public List<T> exploracaoEmLargura(T verticeInicial) {
        Queue<T> fila = new LinkedList<>();
        HashMap<T, T> predecessores = new HashMap<>();
        visitados = new ArrayList<>();
        if (verticeInicial == null || !meuGrafo.containsKey(verticeInicial)) {
            return visitados;
        }
        fila.add(verticeInicial);
        while (!fila.isEmpty()) {
            T verticeAtual = fila.poll();
            visitados.add(verticeAtual);
            LinkedList<Aresta<T>> adjacencias = meuGrafo.get(verticeAtual);
            if (adjacencias != null) {
                for (Aresta<T> aresta : adjacencias) {
                    if (!visitados.contains(aresta.vertice) && !fila.contains(aresta.vertice)) {
                        fila.add(aresta.vertice);
                        predecessores.put(aresta.vertice, verticeAtual);
                    }
                }
            }
        }
        System.out.println("Rotas ate o vertice inicial (Predecessores):");
        for (Map.Entry<T, T> entry : predecessores.entrySet()) {
            System.out.println("Vertice: " + entry.getKey() + " <- Predecessor: " + entry.getValue());
        }
        return visitados;
    }

    public List<T> dijkstra(T origem, T destino) {
        List<T> caminho = new ArrayList<>();
        if (origem == null || destino == null
                || !meuGrafo.containsKey(origem) || !meuGrafo.containsKey(destino)) {
            return caminho;
        }
        for (LinkedList<Aresta<T>> adjacencias : meuGrafo.values()) {
            for (Aresta<T> aresta : adjacencias) {
                if (aresta.peso < 0) {
                    throw new IllegalArgumentException("Dijkstra exige pesos nao negativos.");
                }
            }
        }
        Map<T, Integer> distancias = new HashMap<>();
        Map<T, T> predecessores = new HashMap<>();
        PriorityQueue<DistanciaNode<T>> filaPrioridade = new PriorityQueue<>();
        for (T vertice : meuGrafo.keySet()) {
            distancias.put(vertice, Integer.MAX_VALUE);
            predecessores.put(vertice, null);
        }
        distancias.put(origem, 0);
        filaPrioridade.add(new DistanciaNode<>(origem, 0));
        while (!filaPrioridade.isEmpty()) {
            DistanciaNode<T> atual = filaPrioridade.poll();
            T verticeAtual = atual.vertice;
            int distanciaAtual = atual.distancia;
            if (distanciaAtual > distancias.get(verticeAtual)) {
                continue; // Ignora uma entrada antiga da fila.
            }
            if (verticeAtual.equals(destino)) {
                break;
            }
            for (Aresta<T> adj : meuGrafo.get(verticeAtual)) {
                long novaDistancia = (long) distanciaAtual + adj.peso;
                if (novaDistancia < distancias.get(adj.vertice)) {
                    distancias.put(adj.vertice, (int) novaDistancia);
                    predecessores.put(adj.vertice, verticeAtual);
                    filaPrioridade.add(new DistanciaNode<>(adj.vertice, (int) novaDistancia));
                }
            }
        }
        if (distancias.get(destino) == Integer.MAX_VALUE) {
            System.out.println("Nao existe caminho entre a origem e o destino.");
            return caminho;
        }
        for (T v = destino; v != null; v = predecessores.get(v)) {
            caminho.add(v);
        }
        Collections.reverse(caminho);
        System.out.println("Distancia ate " + destino + ": " + distancias.get(destino) + " km");
        System.out.println("Vetores de distancias: " + distancias);
        System.out.println("Vetores de predecessores: " + predecessores);
        return caminho;
    }
}
