import java.util.ArrayList;
import java.util.List;

public class DFS {
    public static List<Integer> executar( Grafo grafo, int verticeInicial ) {

        // Verifica se o vertice inicial existe.
        if (verticeInicial < 0 || verticeInicial >= grafo.getQuantidadeVertices()) {
            throw new IllegalArgumentException( "Vertice inicial invalido." );
        }

        // Vetor para controlar os vertices visitados.
        boolean[] visitado = new boolean[grafo.getQuantidadeVertices()];

        // Lista que armazena a ordem de visita.
        List<Integer> ordemVisita = new ArrayList<>();

        // Inicia a busca recursiva.
        visitar(grafo, verticeInicial, visitado, ordemVisita);

        // Retorna o resultado.
        return ordemVisita;
    }

    private static void visitar( Grafo grafo, int atual, boolean[] visitado, List<Integer> ordemVisita) {

        // Marca o vertice atual como visitado.
        visitado[atual] = true;

        // Registra a visita.
        ordemVisita.add(atual);

        // Obtém os vizinhos do vertice atual.
        List<Aresta> vizinhos = grafo.getVizinhos(atual);

        // Percorre os vizinhos.
        for (Aresta aresta : vizinhos) {
            int destino = aresta.getDestino();

            // Se ainda nao foi visitado, continua a busca.
            if (!visitado[destino]) {
                visitar( grafo, destino, visitado, ordemVisita );
            }}}}
