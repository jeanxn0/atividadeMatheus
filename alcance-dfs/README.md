# Atividade 24 - Alcance DFS
Jean Santos - Estrutura de Dados II

Implementação baseada no projeto Grafos2026 do professor Matheus Eloy Franco.
A opção 4 consulta se existe caminho entre dois CEPs por busca em profundidade. A lista de visitados evita ciclos. As comparações usam equals, e CEPs inexistentes recebem uma mensagem no menu.

Execute dentro desta pasta com um JDK instalado:

```sh
javac -d bin src/*.java
java -cp bin App
```

Testado com Java 21:
- Não direcionado: 555 para 280 retorna que há caminho.
- Direcionado: 280 para 111 retorna que não há caminho.
- Direcionado: 111 para 280 retorna que há caminho.
- CEP 999 no menu é informado como não cadastrado.
- Ciclos, vértice isolado, mesmo vértice, entradas inexistentes, null, buscas repetidas e objetos equivalentes por equals foram verificados.

Fonte: https://github.com/matheuefranco/Grafos2026
