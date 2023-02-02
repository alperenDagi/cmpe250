import java.util.*;

public class Graph {
    private ArrayList<Vertex> graph;

    public Graph() {
        graph = new ArrayList<>();
    }

    public boolean BFS(Vertex source) {
        for (Vertex vertex : graph) {
            vertex.level = -1;
        }
        source.level =0;
        LinkedList<Vertex> queue = new LinkedList<>();
        queue.addLast(source);
        while(!queue.isEmpty()){
            Vertex u = queue.removeFirst();
            for(Edge edge :u.edges){
                if(edge.flow<edge.capacity && edge.getTo().level<0){
                    edge.getTo().level = u.level+1;
                    queue.addLast(edge.getTo());
                }
            }
        }
        return graph.get(graph.size() - 1).level > 0;
    }
    public int DFS(Vertex source,int flow,Vertex sink,int[] start){
        if(source == sink){
            return flow;
        }
        for(int index = graph.indexOf(source);start[index]<source.edges.size();start[index]++){
            Edge tempEdge = source.edges.get(start[index]);
            if(tempEdge.getTo().level == tempEdge.getFrom().level+1 && tempEdge.flow<tempEdge.capacity){
                int currentFlow = Math.min(flow,tempEdge.capacity-tempEdge.flow);
                int tempFlow = DFS(tempEdge.getTo(), currentFlow,sink,start);
                if(tempFlow >0){
                    tempEdge.flow +=tempFlow;
                    tempEdge.residual.flow -=tempFlow;
                    return tempFlow;
                }
            }
        }
        return 0;
    }
    public int Dinic(Vertex source, Vertex sink){
        int result =0;
        while (BFS(source)){
            int[] start = new int[graph.size()];
            for(int flow =DFS(source,Integer.MAX_VALUE,sink,start); flow !=0; flow =DFS(source,Integer.MAX_VALUE,sink,start)){
                result += flow;
            }
        }
        return result;
    }

    public ArrayList<Vertex> getGraph() {
        return graph;
    }
}
