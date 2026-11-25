
import java.util.ArrayList;
import java.util.List;

public class GrafoListaAdjacencia implements Grafo {
    private List<List<Aresta>> lista;
    private boolean direcionado;
    private boolean ponderado;
    private int quantidadeArestas;

    public GrafoListaAdjacencia(
            int quantidadeVertices,
            boolean direcionado,
            boolean ponderado) {
        if (quantidadeVertices < 0) {
            throw new IllegalArgumentException(
                "Quantidade de vertices invalida."
            );
        }
        this.lista = new ArrayList<>();
        this.direcionado = direcionado;
        this.ponderado = ponderado;
        this.quantidadeArestas = 0;

        for (int i = 0; i < quantidadeVertices; i++) {
            lista.add(new ArrayList<>());
        }
    }

    @Override
    public int adicionarVertice() {
        lista.add(new ArrayList<>());
        return lista.size() - 1;
    }

    @Override
    public void adicionarAresta(int origem, int destino) {
        adicionarAresta(origem, destino, 1.0);
    }

    @Override
    public void adicionarAresta( int origem, int destino, double peso ) {
        validarVertice(origem);
        validarVertice(destino);
        if (!Double.isFinite(peso)) {
            throw new IllegalArgumentException(
                "O peso deve ser um numero finito."
            );
        }
        if (!ponderado) {
            peso = 1.0;
        }
        boolean novaAresta = !existeAresta(origem, destino);
        inserirOuAtualizar(origem, destino, peso);
        if (!direcionado && origem != destino) {
            inserirOuAtualizar(destino, origem, peso);
        }
        if (novaAresta) {
            quantidadeArestas++;
        }
    }

    private void inserirOuAtualizar(
            int origem, int destino, double peso) {
        for (int i = 0; i < lista.get(origem).size(); i++) {
            Aresta aresta = lista.get(origem).get(i);
            if (aresta.getDestino() == destino) {
                lista.get(origem).set( i, new Aresta(origem, destino, peso));
                return;
            }
        }
        lista.get(origem).add( new Aresta(origem, destino, peso) );
    }

    private boolean existeAresta(int origem, int destino) {
        for (Aresta aresta : lista.get(origem)) {
            if (aresta.getDestino() == destino) {
                return true;
            }
        }
        return false;
    }

    private void validarVertice(int vertice) {
        if (vertice < 0 || vertice >= lista.size()) {
            throw new IllegalArgumentException(
                "Vertice inexistente: " + vertice
            );
        }
    }

    @Override
    public List<Aresta> getVizinhos(int vertice) {
        validarVertice(vertice);
        return new ArrayList<>(lista.get(vertice));
    }

    @Override
    public int getQuantidadeVertices() {
        return lista.size();
    }

    @Override
    public int getQuantidadeArestas() {
        return quantidadeArestas;
    }

    @Override
    public boolean isDirecionado() {
        return direcionado;
    }

    @Override
    public boolean isPonderado() {
        return ponderado;
    }

    @Override
    public void imprimirGrafo() {
        System.out.println("LISTA DE ADJACENCIA");
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(i + " -> ");
            for (Aresta aresta : lista.get(i)) {
                System.out.print(
                    "(" + aresta.getDestino()
                    + ", peso: " + aresta.getPeso() + ") "
                );
            }
            System.out.println();
        }
        System.out.println("Vertices: " + getQuantidadeVertices());
        System.out.println("Arestas: " + quantidadeArestas);
    }
}
