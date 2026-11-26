import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Kruskal {
    public static List<Aresta> executar(Grafo grafo) {

        // Verifica se o grafo e nao direcionado.
        if (grafo.isDirecionado()) {
            throw new IllegalArgumentException( "Kruskal exige um grafo nao direcionado.");
        }

        // Verifica se o grafo e ponderado.
        if (!grafo.isPonderado()) {
            throw new IllegalArgumentException( "Kruskal exige um grafo ponderado." );
        }

        int quantidadeVertices = grafo.getQuantidadeVertices();
        List<Aresta> resultado = new ArrayList<>();

        // Um grafo com zero ou um vertice nao possui arestas na AGM.
        if (quantidadeVertices <= 1) {
            return resultado;
        }

        // Reune todas as arestas do grafo.
        List<Aresta> arestas = new ArrayList<>();
        for (int origem = 0; origem < quantidadeVertices; origem++) {
            List<Aresta> vizinhos = grafo.getVizinhos(origem);
            for (Aresta aresta : vizinhos) {

                // Evita adicionar duas vezes cada aresta nao direcionada.
                if (origem < aresta.getDestino()) {
                    arestas.add(aresta);
                }
            }
        }

        // Ordena as arestas do menor para o maior peso.
        Collections.sort(
            arestas,
            Comparator.comparingDouble(Aresta::getPeso)
        );

        // Cria a estrutura Union-Find.
        int[] pai = new int[quantidadeVertices];
        int[] tamanho = new int[quantidadeVertices];

        for (int i = 0; i < quantidadeVertices; i++) {
            pai[i] = i;
            tamanho[i] = 1;
        }

        // Percorre as arestas em ordem crescente.
        for (Aresta aresta : arestas) {
            int origem = aresta.getOrigem();
            int destino = aresta.getDestino();
            int raizOrigem = encontrar(pai, origem);
            int raizDestino = encontrar(pai, destino);

            // Se as raizes forem diferentes, nao existe ciclo.
            if (raizOrigem != raizDestino) {
                resultado.add(aresta);

                // Une os dois conjuntos.
                unir(pai, tamanho, raizOrigem, raizDestino);

                // A AGM possui V - 1 arestas.
                if (resultado.size() == quantidadeVertices - 1) {
                    break;
                }
            }
        }

        // Se nao conectou todos os vertices, o grafo e desconexo.
        if (resultado.size() != quantidadeVertices - 1) {
            throw new IllegalStateException( "Nao existe arvore geradora minima: grafo desconexo." );
        }
        return resultado;
    }

    // Encontra a raiz do conjunto de um vertice.
    private static int encontrar(int[] pai, int vertice) {
        if (pai[vertice] != vertice) {
            pai[vertice] = encontrar(pai, pai[vertice]);
        }

        return pai[vertice];
    }

    // Une dois conjuntos utilizando seus tamanhos.
    private static void unir( int[] pai, int[] tamanho, int raizA, int raizB) {
        if (tamanho[raizA] < tamanho[raizB]) {
            pai[raizA] = raizB;
            tamanho[raizB] += tamanho[raizA];
        } else {
            pai[raizB] = raizA;
            tamanho[raizA] += tamanho[raizB];
        }
    }
}
