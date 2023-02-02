public class Edge {
    public Node target;
    public Node source;
    public int weight;
    public Edge(Node source,Node target,int weight){
        this.target = target;
        this.weight = weight;
    }
}
