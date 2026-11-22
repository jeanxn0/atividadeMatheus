public class DistanciaNode<T> implements Comparable<DistanciaNode<T>> {

    T vertice;
    int distancia;

    DistanciaNode(T vertice, int distancia) {
        this.vertice = vertice;
        this.distancia = distancia;
    }
    @Override
    public int compareTo(DistanciaNode<T> other) {
        return Integer.compare(this.distancia, other.distancia);
    }

    
}
