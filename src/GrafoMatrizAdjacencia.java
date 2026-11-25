
import java.util.ArrayList;
import java.util.List;

public class GrafoMatrizAdjacencia implements Grafo {
    private List<List<Double>> matriz;
    private boolean direcionado;
    private boolean ponderado;
    private int quantidadeArestas;
    public GrafoMatrizAdjacencia(
            int quantidadeVertices,
            boolean direcionado,
            boolean ponderado) {
        if (quantidadeVertices < 0) {
            throw new IllegalArgumentException(
                "Quantidade de vertices invalida."
            );
        }
        this.matriz = new ArrayList<>();
        this.direcionado = direcionado;
        this.ponderado = ponderado;
        this.quantidadeArestas = 0;
        for (int i = 0; i < quantidadeVertices; i++) {
            List<Double> linha = new ArrayList<>();
            for (int j = 0; j < quantidadeVertices; j++) {

                linha.add(Double.POSITIVE_INFINITY);
            }
            matriz.add(linha);
        }
    }

    @Override
    public int adicionarVertice() {
        int tamanhoAtual = matriz.size();
        // Adiciona uma nova coluna em cada linha existente.
        for (List<Double> linha : matriz) {
            linha.add(Double.POSITIVE_INFINITY);
        }

        // Cria a nova linha.
        List<Double> novaLinha = new ArrayList<>();
        for (int j = 0; j <= tamanhoAtual; j++) {
            novaLinha.add(Double.POSITIVE_INFINITY);
        }
        matriz.add(novaLinha);
        return tamanhoAtual;
    }

    @Override
    public void adicionarAresta(int origem, int destino) {
        adicionarAresta(origem, destino, 1.0);
    }

    @Override
    public void adicionarAresta(
            int origem, int destino, double peso) {
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
        matriz.get(origem).set(destino, peso);
        if (!direcionado && origem != destino) {
            matriz.get(destino).set(origem, peso);
        }

        if (novaAresta) {
            quantidadeArestas++;
        }
    }

    private boolean existeAresta(int origem, int destino) {
        return matriz.get(origem).get(destino)
                != Double.POSITIVE_INFINITY;
    }

    private void validarVertice(int vertice) {
        if (vertice < 0 || vertice >= matriz.size()) {
            throw new IllegalArgumentException(
                "Vertice inexistente: " + vertice
            );
        }
    }

    @Override
    public List<Aresta> getVizinhos(int vertice) {
        validarVertice(vertice);
        List<Aresta> vizinhos = new ArrayList<>();
        for (int j = 0; j < matriz.size(); j++) {
            double peso = matriz.get(vertice).get(j);
            if (peso != Double.POSITIVE_INFINITY) {
                vizinhos.add(
                    new Aresta(vertice, j, peso)
                );
            }
        }
        return vizinhos;
    }

    @Override
    public int getQuantidadeVertices() {
        return matriz.size();
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
        System.out.println("MATRIZ DE ADJACENCIA");
        System.out.print("    ");
        for (int j = 0; j < matriz.size(); j++) {
            System.out.printf("%10d", j);
        }

        System.out.println();

        for (int i = 0; i < matriz.size(); i++) {
            System.out.printf("%3d ", i);
            for (int j = 0; j < matriz.size(); j++) {
                double valor = matriz.get(i).get(j);
                if (valor == Double.POSITIVE_INFINITY) {
                    System.out.printf("%10s", "-");
                } else {
                    System.out.printf("%10.2f", valor);
                }
            }

            System.out.println();
        }
        System.out.println("Vertices: " + getQuantidadeVertices());
        System.out.println("Arestas: " + quantidadeArestas);
    }
}
