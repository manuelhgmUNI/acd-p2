import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    public static List<Integer> executar( Grafo grafo, int verticeInicial ) {

        // Verifica se o vertice inicial existe.
        if (verticeInicial < 0 || verticeInicial >= grafo.getQuantidadeVertices()) {
            throw new IllegalArgumentException("Vertice inicial invalido.");
        }

        // Guarda quais vertices ja foram visitados.
        boolean[] visitado = new boolean[grafo.getQuantidadeVertices()];

        // Fila utilizada pela busca em largura.
        Queue<Integer> fila = new LinkedList<>();

        // Guarda a ordem em que os vertices foram visitados.
        List<Integer> ordemVisita = new ArrayList<>();

        // Marca o vertice inicial como visitado.
        visitado[verticeInicial] = true;

        // Insere o vertice inicial na fila.
        fila.add(verticeInicial);

        // Continua enquanto existirem vertices na fila.
        while (!fila.isEmpty()) {

            // Retira o primeiro vertice da fila.
            int atual = fila.poll();

            // Registra o vertice visitado.
            ordemVisita.add(atual);

            // Consulta os vizinhos do vertice atual.
            List<Aresta> vizinhos = grafo.getVizinhos(atual);

            // Percorre todos os vizinhos.
            for (Aresta aresta : vizinhos) {

                int destino = aresta.getDestino();

                // Verifica se o vizinho ainda nao foi visitado.
                if (!visitado[destino]) {

                    // Marca como visitado.
                    visitado[destino] = true;

                    // Adiciona o vizinho na fila.
                    fila.add(destino);
                }
            }
        }
        // Retorna a ordem da busca.
        return ordemVisita;
    }
}
