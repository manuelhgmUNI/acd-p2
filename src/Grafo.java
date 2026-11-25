import java.util.List;

public interface Grafo {
    int adicionarVertice();
    void adicionarAresta(int origem, int destino);
    void adicionarAresta(int origem, int destino, double peso);
    List<Aresta> getVizinhos(int vertice);
    int getQuantidadeVertices();
    int getQuantidadeArestas();
    boolean isDirecionado();
    boolean isPonderado();
    void imprimirGrafo();
}
