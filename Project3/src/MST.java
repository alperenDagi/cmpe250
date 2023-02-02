import java.util.*;

public class MST {
    HashSet<Node> visited;
    TreeSet<Node> unvisited;
    int V;
    int tax;
    Graph graph;
    public MST(int V){
        this.V = V;
        visited = new HashSet<>();
        unvisited = new TreeSet<>();
    }
    public int mst(Graph graph,Node source){
        source.setDistance(0);
        this.graph = graph;
        unvisited.add(source);
        while(unvisited.size() != 0){
            Node u = unvisited.pollFirst();
            if (u.getDistance()== Integer.MAX_VALUE) break;
            updateAdjacent(u);
            visited.add(u);
        }
        if(graph.getNodes().keySet().size()!= visited.size()){
                return -2;
        }
        for (String key:graph.getNodes().keySet()){
            tax += graph.getNodes().get(key).getDistance()*2;
        }
        return tax;
    }
    public void updateAdjacent(Node u){
        int edgeDistance;
        int newDistance;
        for(Node tempNode: graph.getNodes().get(u.getName()).getAdjacentNodes().keySet()){
            if(!visited.contains(tempNode) && !tempNode.getName().equals(u.getName())){
                unvisited.remove(tempNode);
                edgeDistance =u.getAdjacentNodes().get(tempNode);
                newDistance = edgeDistance;
                if(newDistance <= tempNode.getDistance()){
                    tempNode.setDistance(newDistance);
                }
                unvisited.add(tempNode);
            }
        }
    }
}
