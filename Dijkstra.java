
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra 
{
    private Grafo grafo;

    public Dijkstra(Grafo grafo) 
    {
        this.grafo = grafo;
    }

    public double[] calcular(int origem) 
    {

        int n = grafo.getQuantidadeVertices();

        if (origem < 0 || origem >= n) 
        {
            System.err.println("Vertice de origem invalido.");
            return null;
        }

        double[]  distancias = new double[n];
        int[]     anteriores = new int[n];
        boolean[] visitados  = new boolean[n];

        Arrays.fill(distancias, Double.POSITIVE_INFINITY);
        Arrays.fill(anteriores, -1);
        distancias[origem] = 0;


        PriorityQueue<int[]> fila = new PriorityQueue<>((a, b) -> Double.compare(distancias[a[0]], distancias[b[0]]));
        fila.add(new int[]{origem});

        while (!fila.isEmpty()) 
        {

            int atual = fila.poll()[0];

            if (visitados[atual]) 
            {
                continue;
            }

            visitados[atual] = true;

            // Percorre os vizinhos do vertice atual.
            for (Aresta aresta : grafo.getVizinhos(atual)) 
            {

                int destino = aresta.getDestino();
                double peso = aresta.getPeso();

                if (peso < 0) 
                {
                    System.err.println("Dijkstra nao aceita pesos negativos.");
                    return null;
                }

                if (!visitados[destino]) 
                {

                    double novaDistancia = distancias[atual] + peso;

                    // Verifica se encontra um caminho menor.
                    if (novaDistancia < distancias[destino]) 
                    {
                        distancias[destino] = novaDistancia;
                        anteriores[destino] = atual;
                        fila.add(new int[]{destino});
                    }
                }
            }
        }

        return distancias;
    }
}
