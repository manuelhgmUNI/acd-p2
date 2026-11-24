import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class OrdenacaoTopologica {
    public static List<Integer> executar(Grafo grafo) {

        // A ordenacao topologica exige um grafo direcionado.
        if (!grafo.isDirecionado()) {
            throw new IllegalArgumentException( "A ordenacao topologica exige um grafo direcionado." );
        }
        int quantidadeVertices = grafo.getQuantidadeVertices();

        // Guarda o grau de entrada de cada vertice.
        int[] grauEntrada = new int[quantidadeVertices];

        // Fila dos vertices que possuem grau de entrada zero.
        Queue<Integer> fila = new ArrayDeque<>();

        // Guarda a ordem topologica encontrada.
        List<Integer> ordem = new ArrayList<>();

        // Calcula o grau de entrada de cada vertice.
        for (int origem = 0; origem < quantidadeVertices; origem++) {
            List<Aresta> vizinhos = grafo.getVizinhos(origem);
            for (Aresta aresta : vizinhos) {
                int destino = aresta.getDestino();
                grauEntrada[destino]++;
            }
        }

        // Adiciona na fila todos os vertices com grau de entrada zero.
        for (int vertice = 0; vertice < quantidadeVertices; vertice++) {
            if (grauEntrada[vertice] == 0) {
                fila.add(vertice);
            }
        }

        // Executa o algoritmo enquanto houver vertices na fila.
        while (!fila.isEmpty()) {

            // Retira um vertice da fila.
            int atual = fila.poll();

            // Adiciona o vertice na ordenacao.
            ordem.add(atual);

            // Percorre os vizinhos do vertice atual.
            List<Aresta> vizinhos = grafo.getVizinhos(atual);

            for (Aresta aresta : vizinhos) {
                int destino = aresta.getDestino();

                // Remove logicamente a aresta atual.
                grauEntrada[destino]--;

                // Se o grau de entrada chegou a zero,
                // o vertice pode ser processado.
                if (grauEntrada[destino] == 0) {
                    fila.add(destino);
                }
            }
        }

        // Se nem todos os vertices foram processados, existe pelo menos um ciclo no grafo.
        if (ordem.size() != quantidadeVertices) {
            throw new IllegalStateException("Nao existe ordenacao topologica: o grafo possui ciclo.");
        }
        return ordem;
    }
}
