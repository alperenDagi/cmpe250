import java.util.ArrayList;

public class Vertex {
    int level;
    ArrayList<Edge> edges;
    String name;
    public Vertex(String name) {
        this.name = name;
        edges = new ArrayList<>();
    }
    public void addEdge(Vertex to, int capacity) {
        if (capacity >0){
            Edge tempEdge1 = new Edge(this, to, capacity);
            Edge tempEdge2 = new Edge(to, this, 0);
            tempEdge1.residual = tempEdge2;
            tempEdge2.residual = tempEdge1;
            this.edges.add(tempEdge1);
            to.edges.add(tempEdge2);
        }
    }

}

