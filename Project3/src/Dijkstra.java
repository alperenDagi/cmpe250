import java.io.PrintStream;
import java.util.HashSet;
import java.util.TreeSet;

public class Dijkstra {
    HashSet<Node> visited;
    TreeSet<Node> unvisited;
    PrintStream out ;
    int V;
    Graph graph;
    String result = "";
    public Dijkstra(int V,PrintStream out){
        this.out = out;
        this.V = V;
        visited = new HashSet<>();
        unvisited = new TreeSet<>();
    }
    public void dijkstra(Graph graph,Node source,String leyla){
        source.setDistance(0);
        this.graph = graph;
        unvisited.add(source);
        while(unvisited.size() > 0){
            Node u = unvisited.pollFirst();
            if (u.getDistance()== Integer.MAX_VALUE) break;
            updateAdjacent(u);
            visited.add(u);
        }
        Node temp =graph.getNodes().get(leyla);
        if(temp.getDistance() == Integer.MAX_VALUE){
            out.println(-1);
            return;
        }
        while(temp.getParent()!= null){
            result = temp.getParent().getName()+" "+result;
            temp = temp.getParent();
        }
        result= result+leyla;
        out.println(result);
//        System.out.println(result);
    }
    public void updateAdjacent(Node u){
        int edgeDistance;
        int newDistance;
        for(Node tempNode: graph.getNodes().get(u.getName()).getAdjacentNodes().keySet()){
            if(!visited.contains(tempNode)){
                unvisited.remove(tempNode);
                edgeDistance =u.getAdjacentNodes().get(tempNode);
                newDistance = edgeDistance+ u.getDistance();
                if(newDistance < tempNode.getDistance()){
                    tempNode.setParent(u);
                    tempNode.setDistance(newDistance);
                }
                unvisited.add(tempNode);
            }
        }
    }
}
