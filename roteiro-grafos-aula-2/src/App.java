import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class App {

    static void carregarCidades(Grafo<Cidade> meuGrafo, HashMap<Integer, Cidade> cepCidades, String caminhoArquivo) throws Exception {
        String linha;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(caminhoArquivo), StandardCharsets.UTF_8)) {
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(","); // divide os campos por vírgula
                String nome = campos[0].trim();
                String estado = campos[1].trim();
                int cep = Integer.parseInt(campos[2].trim());
                Cidade cidade = new Cidade(nome, estado, cep);
                meuGrafo.adicionarVertice(cidade);
                cepCidades.put(cep, cidade);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void carregarArestas(Grafo<Cidade> meuGrafo, HashMap<Integer, Cidade> cepCidades, String caminhoArquivo, boolean direcionado) throws Exception {
        String linha;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(caminhoArquivo), StandardCharsets.UTF_8)) {
            while ((linha = br.readLine()) != null) {
                String[] campos = linha.split(","); // divide os campos por vírgula
                int cepOrigem = Integer.parseInt(campos[0].trim());
                int cepDestino = Integer.parseInt(campos[1].trim());
                int peso = Integer.parseInt(campos[2].trim());
                Cidade origem = cepCidades.get(cepOrigem);
                Cidade destino = cepCidades.get(cepDestino);
                meuGrafo.adicionarAresta(origem, destino, peso, direcionado);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void ordenarAdjacencias(Grafo<Cidade> grafo) {
        // As figuras do roteiro visitam os vizinhos por CEP crescente.
        for (LinkedList<Aresta<Cidade>> adjacencias : grafo.meuGrafo.values()) {
            adjacencias.sort(Comparator.comparing(aresta -> aresta.vertice));
        }
    }

    static void menu() {
        System.out.println("1. Adicionar Vértice");
        System.out.println("2. Adicionar Aresta");
        System.out.println("3. Mostrar Grafo");
        System.out.println("4. Verificar Alcance");
        System.out.println("5. Busca em Profundidade DFS");
        System.out.println("6. Exploração em Largura BFS");
        System.out.println("7. Menor Caminho com Dijkstra");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Grafo<Cidade> grafo = new Grafo<>();
        HashMap<Integer, Cidade> cepCidades = new HashMap<>();
        int opcao;
        boolean direcionado = false;
        System.out.println("Tipo de grafo:  1. Não Direcionado  2. Direcionado");
        int tipoGrafo = scanner.nextInt();
        if (tipoGrafo == 2) {
            direcionado = true;
        }
        carregarCidades(grafo, cepCidades , "cidades5.csv");
        carregarArestas(grafo, cepCidades, "arestas5.csv", direcionado);
        ordenarAdjacencias(grafo);
        do {
            menu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // <-- limpa o buffer antes de ler texto
            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome da Cidade: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o estado da Cidade: ");
                    String estado = scanner.nextLine();
                    System.out.print("Digite o CEP da Cidade: ");
                    int cep = scanner.nextInt();
                    Cidade cidade = new Cidade(nome, estado, cep);
                    if (grafo.adicionarVertice(cidade)) {
                        System.out.println("Vértice adicionado com sucesso.");
                        cepCidades.put(cep, cidade);
                    } else {
                        System.out.println("Vértice já existe.");
                    }
                    break;
                case 2:
                    System.out.print("Digite o vértice de origem: ");
                      int cepOrigem = scanner.nextInt();
                      Cidade origem = cepCidades.get(cepOrigem);
                      System.out.println("Origem selecionada: " + origem);
                     
                    System.out.print("Digite o vértice de destino: ");
                    int cepDestino = scanner.nextInt();
                    Cidade destino = cepCidades.get(cepDestino);
                    System.out.println("Destino selecionado: " + destino);
                    System.out.print("Digite o peso da aresta: ");
                    int peso = scanner.nextInt();
                    if (peso < 0) {
                        System.out.println("Informe um peso nao negativo para usar Dijkstra.");
                        break;
                    }
                    if (grafo.adicionarAresta(origem, destino, peso, direcionado)) {
                        ordenarAdjacencias(grafo);
                        System.out.println("Aresta adicionada com sucesso.");
                    } else {
                        System.out.println("Erro ao adicionar aresta.");
                    }
                    break;
                case 3:
                    grafo.mostrarGrafo();
                    break;
                case 4:
                    System.out.print("Digite o vértice de origem: ");
                    int cepOrigemAlcance = scanner.nextInt();
                    Cidade origemAlcance = cepCidades.get(cepOrigemAlcance);
                    System.out.print("Digite o vértice de destino: ");
                    int cepDestinoAlcance = scanner.nextInt();
                    Cidade destinoAlcance = cepCidades.get(cepDestinoAlcance);
                    if (origemAlcance == null || destinoAlcance == null) {
                        System.out.println("CEP não cadastrado.");
                        break;
                    }
                    if (grafo.alcance(origemAlcance, destinoAlcance)) {
                        System.out.println("Há um caminho entre " + origemAlcance + " e " + destinoAlcance);
                    } else {
                        System.out.println("Não há um caminho entre " + origemAlcance + " e " + destinoAlcance);
                    }
                    break;
                case 5:
                    System.out.print("Digite o vértice inicial para DFS: ");
                    int cepInicialDFS = scanner.nextInt();
                    Cidade inicialDFS = cepCidades.get(cepInicialDFS);
                    if (inicialDFS == null) {
                        System.out.println("CEP nao cadastrado.");
                        break;
                    }
                    List<Cidade> resultadoDFS = grafo.buscaEmProfundidade(inicialDFS);
                    System.out.println("Ordem de visitação (DFS): " + resultadoDFS);
                    break;
                case 6:
                    System.out.print("Digite o vértice inicial para BFS: ");
                    int cepInicialBFS = scanner.nextInt();
                    Cidade inicialBFS = cepCidades.get(cepInicialBFS);
                    if (inicialBFS == null) {
                        System.out.println("CEP nao cadastrado.");
                        break;
                    }
                    List<Cidade> resultadoBFS = grafo.exploracaoEmLargura(inicialBFS);
                    System.out.println("Ordem de visitação (BFS): " + resultadoBFS);
                    break;
                case 7:
                    System.out.print("Digite o vértice de origem para Dijkstra: ");
                    int cepOrigemDijkstra = scanner.nextInt();
                    Cidade origemDijkstra = cepCidades.get(cepOrigemDijkstra);
                    System.out.print("Digite o vértice de destino: ");
                    cepDestino = scanner.nextInt();
                    destino = cepCidades.get(cepDestino);
                    if (origemDijkstra == null || destino == null) {
                        System.out.println("CEP nao cadastrado.");
                        break;
                    }
                    List<Cidade> caminho = grafo.dijkstra(origemDijkstra, destino);
                    System.out.println("Menores caminhos a partir de " + origemDijkstra + "ate " + destino + ":"); 
                    for (Cidade c : caminho) {
                        System.out.println("|" + c);
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
