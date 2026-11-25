# Roteiro de Grafos Aula 2

Atividade 26 de Estrutura de Dados II, professor Matheus Eloy Franco.
Projeto Java baseado no projeto de grafos da aula: lista de adjacência, alcance,
busca em profundidade (DFS), busca em largura (BFS) e Dijkstra.

O menu permite cadastrar cidades e estradas e executar as buscas. Os arquivos
`cidades5.csv` e `arestas5.csv` contêm as oito cidades do roteiro. As listas de
vizinhos são ordenadas por CEP no App para reproduzir a ordem das figuras.

## Executar

Com JDK 21 ou superior, dentro desta pasta:

```sh
javac -encoding UTF-8 -d bin src/*.java
java -cp bin App
```

## Testar

```sh
javac -encoding UTF-8 -d bin src/*.java testes/TesteGrafo.java
java -cp bin TesteGrafo
```

As verificações cobrem a ordem de DFS e BFS, a rota de 558 km entre Andradas
e Belo Horizonte, ciclos, cidades isoladas, CEP inválido, buscas repetidas,
grafo direcionado e rejeição de pesos negativos no Dijkstra.

Resultados esperados usando os CEPs:

- DFS de 111: 111, 222, 333, 444, 616, 888, 555, 280.
- BFS de 111: 111, 222, 280, 333, 444, 616, 888, 555.
- Menor rota de 555 a 888: 555, 333, 111, 222, 888; 558 km.

Dijkstra exige pesos não negativos. As distâncias são inteiras e devem ser
menores que `Integer.MAX_VALUE`, usado para representar infinito no roteiro.
