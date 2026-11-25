import java.util.*;

public class TesteGrafo {
    static int verificacoes;

    static void conferir(boolean condicao, String mensagem) {
        if (!condicao) throw new AssertionError(mensagem);
        verificacoes++;
    }

    static List<Integer> ceps(List<Cidade> cidades) {
        List<Integer> resultado = new ArrayList<>();
        for (Cidade cidade : cidades) resultado.add(cidade.getCep());
        return resultado;
    }

    public static void main(String[] args) throws Exception {
        Grafo<Cidade> grafo = new Grafo<>();
        HashMap<Integer, Cidade> cidades = new HashMap<>();
        App.carregarCidades(grafo, cidades, "cidades5.csv");
        App.carregarArestas(grafo, cidades, "arestas5.csv", false);
        App.ordenarAdjacencias(grafo);
        conferir(ceps(grafo.buscaEmProfundidade(cidades.get(111))).equals(
            Arrays.asList(111, 222, 333, 444, 616, 888, 555, 280)), "Ordem DFS da Figura 3");
        conferir(ceps(grafo.exploracaoEmLargura(cidades.get(111))).equals(
            Arrays.asList(111, 222, 280, 333, 444, 616, 888, 555)), "Ordem BFS da Figura 4");
        List<Cidade> rota = grafo.dijkstra(cidades.get(555), cidades.get(888));
        conferir(ceps(rota).equals(Arrays.asList(555, 333, 111, 222, 888)), "Rota otima da Figura 5");
        int distancia = 0;
        for (int i = 1; i < rota.size(); i++) {
            for (Aresta<Cidade> aresta : grafo.meuGrafo.get(rota.get(i - 1))) {
                if (aresta.vertice.equals(rota.get(i))) distancia += aresta.peso;
            }
        }
        conferir(distancia == 558, "Distancia total de 558 km");
        conferir(grafo.alcance(cidades.get(555), cidades.get(888)), "Alcance com ciclo");
        conferir(grafo.dijkstra(cidades.get(111), cidades.get(111)).size() == 1, "Origem igual ao destino");
        conferir(grafo.buscaEmProfundidade(null).isEmpty(), "DFS com origem invalida");
        conferir(grafo.exploracaoEmLargura(null).isEmpty(), "BFS com origem invalida");
        conferir(grafo.dijkstra(null, cidades.get(888)).isEmpty(), "Dijkstra com origem invalida");
        Cidade isolada = new Cidade("Isolada", "MG", 999);
        grafo.adicionarVertice(isolada);
        conferir(grafo.dijkstra(cidades.get(111), isolada).isEmpty(), "Destino inalcançavel");
        conferir(grafo.buscaEmProfundidade(isolada).equals(Arrays.asList(isolada)), "DFS em vertice isolado");
        conferir(!grafo.alcance(cidades.get(111), isolada), "Sem alcance para isolado");
        conferir(grafo.buscaEmProfundidade(new Cidade("Outra instancia", "MG", 111)).size() == 8,
            "Igualdade pelo CEP e buscas repetidas");
        Grafo<Integer> dirigido = new Grafo<>();
        dirigido.adicionarVertice(1);
        dirigido.adicionarVertice(2);
        dirigido.adicionarVertice(3);
        dirigido.adicionarAresta(1, 2, 10, true);
        dirigido.adicionarAresta(1, 3, 1, true);
        dirigido.adicionarAresta(3, 2, 1, true);
        conferir(dirigido.dijkstra(1, 2).equals(Arrays.asList(1, 3, 2)), "Relaxamento de rota mais curta");
        conferir(dirigido.dijkstra(2, 1).isEmpty(), "Respeita sentido das arestas");
        dirigido.adicionarAresta(2, 3, -1, true);
        boolean rejeitou = false;
        try { dirigido.dijkstra(1, 2); }
        catch (IllegalArgumentException e) { rejeitou = true; }
        conferir(rejeitou, "Rejeita pesos negativos");
        System.out.println("OK: " + verificacoes + " verificacoes passaram.");
    }
}
