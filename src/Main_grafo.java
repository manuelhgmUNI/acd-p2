
public class Main_grafo 
{
    public static void main(String[] args) 
    {
        int exatidção = 10; // numero de vezes q os algoritimos vão ser executados pra poder fazer uma média
        int alg_num = 9; // numero de algoritimos
        int ponderado = 2; // se 0 o grafo n é ponderado se 1 grafo ponderado 
        int tam_max;
        if (args.length > 1)
            tam_max = Integer.parseInt(args[1]);
        else 
            tam_max = 100;
        
        if (tam_max % 10 != 0)
            System.err.println("entrada: "+tam_max+"\nporem o segundo termo tem q ser multiplo de 10");

        int tam_vector = tam_max/10;
        long[][][][][][] tempos = new long[alg_num][2][2][ponderado][exatidção][tam_vector]; // o [2] aqui é pra diferenciar da lista e da matriz e o outro é direcionado e n derecionado
        
        // =======================================================================================================
        // execução
        
        for (int i = 0; i < exatidção; i++)
        {
            for (int j = 0; j < ponderado; j++)
            {
                boolean bpon = false; // n ponderado
                if (j == 0) // ponderado
                {
                    bpon = true;
                }

                for (int k = 0; k < 2; k++)
                {
                    boolean bdir = false; // n direcionado
                    if (k == 1) // direcionado
                    {
                        bdir = true;
                    }
                    for (int l = 0; l < 2; l++)
                    {
                        for (int tam = 10 ; tam <= tam_max; tam *= 10)
                        {
                            int tindex = tam/10;
                            tindex -= 1;

                            Grafo grafo;
                            if (l == 0) // lista
                            {
                                grafo = new GrafoListaAdjacencia(tam, bdir, bpon);
                            }
                            else // matriz
                            {
                                grafo = new GrafoMatrizAdjacencia(tam, bdir, bpon);
                            }
                            /*
                            // obrigatorios
                            0 = dijk
                            1 = bell
                            
                            // opicionais
                            2 = floyd
                            3 = ford
                            4 = bfs
                            5 = dfs
                            6 = topo
                            7 = krus
                            8 = prim
                            */
                            int alg_cont = 0;
                            
                            // 0
                            Dijkstra dijkstra = new Dijkstra(grafo);
                            dijkstra.calcular(0);
                            tempos[alg_cont][l][k][j][i][tindex] = dijkstra.tempo();

                            // 1
                            BellmanFord bellmanFord = new BellmanFord(grafo);
                            bellmanFord.calcular(0);
                            tempos[alg_cont][l][k][j][i][tindex] = bellmanFord.tempo();

                            /*
                            // 2
                            FloydWarshall floydWarshall = new FloydWarshall(grafo);
                            floydWarshall.calcular();
                            tempos[alg_cont][l][k][j][i][tindex] = floydWarshall.tempo();
                            
                            // 3
                            FordFulkerson fordFulkerson = new FordFulkerson(grafo);
                            fordFulkerson.calcular(0, i);
                            tempos[alg_cont][l][k][j][i][tindex] = dijkstra.tempo();
                            */

                            




                            
                            
                            
                            
                        }
                    }
                }
            }
        }
        




        // parte antiga
        int test = 0;
        if (args.length > 0)
            test = Integer.parseInt(args[0]);
        //System.out.println(test);
        if (test == 1) 
        {
            // ==================================
            // TESTE 1: LISTA DE ADJACENCIA
            // ==================================

            System.out.println("===== TESTE LISTA =====");

            Grafo lista = new GrafoListaAdjacencia(
                4,
                true,
                true
            );

            lista.adicionarAresta(0, 1, 10);
            lista.adicionarAresta(0, 2, 5);
            lista.adicionarAresta(1, 2, 3);
            lista.adicionarAresta(2, 3, 2);

            lista.imprimirGrafo();


            // ==================================
            // TESTE 2: MATRIZ DE ADJACENCIA
            // ==================================

            System.out.println("\n===== TESTE MATRIZ =====");

            Grafo matriz = new GrafoMatrizAdjacencia(
                4,
                true,
                true
            );

            matriz.adicionarAresta(0, 1, 10);
            matriz.adicionarAresta(0, 2, 5);
            matriz.adicionarAresta(1, 2, 3);
            matriz.adicionarAresta(2, 3, 2);

            matriz.imprimirGrafo();


            // ==================================
            // TESTE 3: CONSULTAR VIZINHOS
            // ==================================

            System.out.println("\n===== VIZINHOS DO VERTICE 0 =====");

            for (Aresta aresta : lista.getVizinhos(0)) {

                System.out.println(aresta);
            }


            // ==================================
            // TESTE 4: ADICIONAR VERTICE
            // ==================================

            System.out.println("\n===== ADICIONANDO VERTICE =====");

            int novoVertice = lista.adicionarVertice();

            System.out.println("Novo vertice: " + novoVertice);

            lista.adicionarAresta(3, novoVertice, 15);

            lista.imprimirGrafo();


            // ==================================
            // TESTE 5: GRAFO NAO DIRECIONADO
            // ==================================

            System.out.println("\n===== GRAFO NAO DIRECIONADO =====");

            Grafo naoDirecionado = new GrafoListaAdjacencia(
                3,
                false,
                true
            );

            naoDirecionado.adicionarAresta(0, 1, 8);
            naoDirecionado.adicionarAresta(1, 2, 4);

            naoDirecionado.imprimirGrafo();


            // ==================================
            // TESTE 6: GRAFO NAO PONDERADO
            // ==================================

            System.out.println("\n===== GRAFO NAO PONDERADO =====");

            Grafo naoPonderado = new GrafoMatrizAdjacencia(
                3,
                true,
                false
            );

            naoPonderado.adicionarAresta(0, 1);
            naoPonderado.adicionarAresta(1, 2);

            naoPonderado.imprimirGrafo();
        }
    }
}
