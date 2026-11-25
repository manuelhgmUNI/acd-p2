
public class FloydWarshall
{
    private Grafo grafo;
    private long tempo;

    public FloydWarshall(Grafo grafo)
    {
        this.grafo = grafo;
    }

    public long tempo()
    {
        return this.tempo;
    }

    public double[][] calcular()
    {
        int n = grafo.getQuantidadeVertices();

        double[][] distancias = new double[n][n];

        // Inicializa a matriz de distancias
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == j)
                {
                    distancias[i][j] = 0; // diagonall principal
                }
                else
                {
                    distancias[i][j] = Double.POSITIVE_INFINITY; // tudo fora dela
                }
            }
        }

        // Adiciona as arestas existentes no grafo
        for (int i = 0; i < n; i++)
        {
            for (Aresta aresta : grafo.getVizinhos(i))
            {
                int destino = aresta.getDestino();
                double peso = aresta.getPeso();

                if (peso < distancias[i][destino])
                {
                    distancias[i][destino] = peso;
                }
            }
        }

        // começo
        this.tempo = System.nanoTime();


        // Algoritmo Floyd-Warshall
        for (int k = 0; k < n; k++)
        {
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (distancias[i][k] != Double.POSITIVE_INFINITY && distancias[k][j] != Double.POSITIVE_INFINITY)
                    {
                        double novaDistancia =
                            distancias[i][k] + distancias[k][j];

                        // Verifica se passar por K oferece um caminho menor
                        if (novaDistancia < distancias[i][j])
                        {
                            distancias[i][j] = novaDistancia;
                        }
                    }
                }
            }
        }
        this.tempo = System.nanoTime() - this.tempo;

        // Verifica a existencia de ciclos negativos
        for (int i = 0; i < n; i++)
        {
            if (distancias[i][i] < 0)
            {
                System.err.println("Grafo possui ciclo negativo.");
                return null;
            }
        }

        return distancias;
    }
}
