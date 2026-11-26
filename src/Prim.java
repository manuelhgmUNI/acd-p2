import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Prim {
    public static List<Aresta> executar(Grafo grafo) {

        // Verifica se o grafo e nao direcionado.
        if (grafo.isDirecionado()) {
            throw new IllegalArgumentException( "Prim exige um grafo nao direcionado." );
        }

        // Verifica se o grafo e ponderado.
        if (!grafo.isPonderado()) {
            throw new IllegalArgumentException( "Prim exige um grafo ponderado." );
        }

        int quantidadeVertices = grafo.getQuantidadeVertices();
        List<Aresta> resultado = new ArrayList<>();

        if (quantidadeVertices <= 1) {
            return resultado;
        }

        // Controla os vertices que ja pertencem a AGM.
        boolean[] visitado = new boolean[quantidadeVertices];

        // Fila que prioriza as arestas de menor peso.
        PriorityQueue<Aresta> fila = new PriorityQueue<>( Comparator.comparingDouble(Aresta::getPeso) );

        // Comeca pelo vertice zero.
        visitado[0] = true;

        // Adiciona as arestas que saem do vertice inicial.
        fila.addAll(grafo.getVizinhos(0));

        // Continua enquanto houver arestas na fila.
        while (!fila.isEmpty() && resultado.size() < quantidadeVertices - 1) {
            // Retira a aresta de menor peso.
            Aresta aresta = fila.poll();
            int origem = aresta.getOrigem();
            int destino = aresta.getDestino();

            // Se os dois vertices ja foram visitados,
            // essa aresta nao deve ser adicionada.
            if (visitado[origem] && visitado[destino]) {
                continue;
            }

            // Descobre qual dos vertices ainda nao foi visitado.
            int novoVertice;

            if (visitado[origem]) {
                novoVertice = destino;
            } else {
                novoVertice = origem;
            }

            // Adiciona a aresta na arvore geradora minima.
            resultado.add(aresta);

            // Marca o novo vertice como visitado.
            visitado[novoVertice] = true;

            // Adiciona as novas arestas disponiveis.
            for (Aresta vizinho : grafo.getVizinhos(novoVertice)) {
                int destinoVizinho = vizinho.getDestino();

                if (!visitado[destinoVizinho]) {
                    fila.add(vizinho);
                }
            }
        }

        // Verifica se todos os vertices foram conectados.
        if (resultado.size() != quantidadeVertices - 1) {
            throw new IllegalStateException( "Nao existe arvore geradora minima: grafo desconexo." );
        }
        return resultado;
    }
}