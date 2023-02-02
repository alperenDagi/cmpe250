import java.util.ArrayList;

public class Edge {
    private final Vertex from;
    private final Vertex to;
    Edge residual;
    int flow;
    int capacity;
    public Edge(Vertex from, Vertex to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
    }
    public Vertex getTo() {
        return to;
    }

    public Vertex getFrom() {
        return from;
    }
}
