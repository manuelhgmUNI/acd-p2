
public class Main_grafo 
{
    public static void main(String[] args) 
    {
        int exatidção = 10; // numero de vezes q os algoritimos vão ser executados pra poder fazer uma média
        int alg_num = 7; // numero de algoritimos
        long[][][] tempos = new long[alg_num][2][exatidção]; // o [2] aqui é pra diferenciar da lista e da matriz
    
        

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
