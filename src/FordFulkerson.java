
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson
{
    private Grafo grafo;
    private long tempo;

    public FordFulkerson(Grafo grafo)
    {
        this.grafo = grafo;
    }

    public long tempo()
    {
        return this.tempo;
    }

    public double calcular(int origem, int destino)
    {
        int n = grafo.getQuantidadeVertices();
        int[] anteriores = new int[n];
        double fluxoMaximo = 0;
        double[][] capacidade = new double[n][n];
        double[][] residual = new double[n][n]; // Matriz de capacidades residuais
        
        if (origem < 0 || origem >= n || destino < 0 || destino >= n || origem == destino)
        {
            System.err.println("Vertices de origem ou destino invalidos.");
            return -1;
        }

        if (!grafo.isDirecionado() || !grafo.isPonderado())
        {
            System.err.println("Ford-Fulkerson exige grafo direcionado e ponderado.");
            return -1;
        }

        // Inicializa a matriz de capacidades
        for (int i = 0; i < n; i++)
        {
            for (Aresta aresta : grafo.getVizinhos(i))
            {
                int destinoAresta = aresta.getDestino();
                double peso = aresta.getPeso();

                if (peso < 0)
                {
                    System.err.println("Capacidades nao podem ser negativas.");
                    return -1;
                }

                capacidade[i][destinoAresta] = peso;
            }
        }

        for (int i = 0; i < n; i++)
        {
            residual[i] = Arrays.copyOf(capacidade[i], n); // passando as listas pra lista uma lista de listas
        }

        // Começo

        this.tempo = System.nanoTime();

        // Procura caminhos aumentantes kkkkk
        while (buscarCaminho(residual, origem, destino, anteriores))
        {
            double fluxoCaminho = Double.POSITIVE_INFINITY;

            // Menor capacidade do caminho
            for (int atual = destino; atual != origem; atual = anteriores[atual])
            {
                int anterior = anteriores[atual];

                fluxoCaminho = Math.min(fluxoCaminho, residual[anterior][atual]);
            }

            // Atualiza as capacidades residuais.
            for (int atual = destino; atual != origem; atual = anteriores[atual])
            {
                int anterior = anteriores[atual];

                residual[anterior][atual] -= fluxoCaminho;
                residual[atual][anterior] += fluxoCaminho;
            }

            fluxoMaximo += fluxoCaminho;
        }

        this.tempo = System.nanoTime() - this.tempo;
        return fluxoMaximo;
    }

    // Busca em largura para encontrar um caminho aumentante
    // Renan, eu n sei direito como integrar o tua bfs aqui, caso tu veja alguma forma pode se sentir livre pra isso
    private boolean buscarCaminho(double[][] residual, int origem, int destino, int[] anteriores)
    {
        int n = residual.length;

        boolean[] visitados = new boolean[n];

        Queue<Integer> fila = new LinkedList<>();

        Arrays.fill(anteriores, -1);

        fila.add(origem);
        visitados[origem] = true;

        while (!fila.isEmpty())
        {
            int atual = fila.poll();

            for (int i = 0; i < n; i++)
            {
                if (!visitados[i] && residual[atual][i] > 0)
                {
                    anteriores[i] = atual;
                    visitados[i] = true;

                    if (i == destino)
                    {
                        return true;
                    }

                    fila.add(i);
                }
            }
        }

        return false;
    }
}
