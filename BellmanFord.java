
import java.util.Arrays;

public class BellmanFord
{
    private Grafo grafo;
    private long tempo;

    public BellmanFord(Grafo grafo)
    {
        this.grafo = grafo;
    }

    public long tempo()
    {
        return this.tempo;
    }

    public double[] calcular(int origem)
    {
        int n = grafo.getQuantidadeVertices();

        if (origem < 0 || origem >= n)
        {
            System.err.println("Vertice de origem invalido.");
            return null;
        }

        double[] distancias = new double[n];

        Arrays.fill(distancias, Double.POSITIVE_INFINITY);

        distancias[origem] = 0;

        this.tempo = System.nanoTime();

        // arestas N - 1 vezes
        for (int i = 0; i < n - 1; i++)
        {
            boolean alterou = false;

            for (int atual = 0; atual < n; atual++)
            {
                if (distancias[atual] == Double.POSITIVE_INFINITY)
                {
                    continue;
                }

                // vizinhos do vertice atual
                for (Aresta aresta : grafo.getVizinhos(atual))
                {
                    int destino = aresta.getDestino();
                    double peso = aresta.getPeso();

                    double novaDistancia = distancias[atual] + peso;

                    // Procura caminho menor
                    if (novaDistancia < distancias[destino])
                    {
                        distancias[destino] = novaDistancia;
                        alterou = true;
                    }
                }
            }

            // verifica se a distancia mudou
            if (!alterou)
            {
                break;
            }
        }

        // Verifica se existe ciclo negativo alcancavel
        for (int atual = 0; atual < n; atual++)
        {
            if (distancias[atual] == Double.POSITIVE_INFINITY)
            {
                continue;
            }

            for (Aresta aresta : grafo.getVizinhos(atual))
            {
                int destino = aresta.getDestino();
                double peso = aresta.getPeso();

                if (distancias[atual] + peso < distancias[destino])
                {
                    System.err.println("Grafo possui ciclo negativo.");
                    return null;
                }
            }
        }
        this.tempo = System.nanoTime() - this.tempo;
        return distancias;
    }
}
