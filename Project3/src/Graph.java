import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Map<String,Node> nodes = new HashMap<>();
    public void addNode (Node node){nodes.put(node.getName(), node);}
    public Map<String, Node> getNodes() {
        return nodes;
    }

    public void toUndirected(){
        for(String key : nodes.keySet()){
            if(!nodes.get(key).getAdjacentNodes().isEmpty()){
                for(Node node: nodes.get(key).getAdjacentNodes().keySet()){
                    if(node != null && node.getAdjacentNodes().containsKey(nodes.get(key))){
                        if(nodes.get(key).getAdjacentNodes().get(node)<node.getAdjacentNodes().get(nodes.get(key))){
//                            System.out.println(key+" "+nodes.get(key).getAdjacentNodes().get(node) +"-->"+node.getName()+" "+node.getAdjacentNodes().get(nodes.get(key)));
                            node.addDestination(nodes.get(key),nodes.get(key).getAdjacentNodes().get(node));
//                            System.out.println(key+" "+nodes.get(key).getAdjacentNodes().get(node) +"-->"+node.getName()+" "+node.getAdjacentNodes().get(nodes.get(key)));
                        }else{
                            nodes.get(key).addDestination(node,node.getAdjacentNodes().get(nodes.get(key)));
                        }
                    }else if(node != null) {
                        node.addDestination(nodes.get(key),nodes.get(key).getAdjacentNodes().get(node));
                    }
                }
            }
        }
    }
}
