import java.util.*;

public class Node implements Comparable<Node>{
    private final String name;
    private int index;
    private Integer distance = Integer.MAX_VALUE;
    private Node parent;
    private boolean inMST;
    private Map<Node, Integer> adjacentNodes;

    public Node(String name) {
        this.name = name;
        index = name.charAt(1);
        parent = null;
        adjacentNodes = new HashMap<>();
    }
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public String getName() {
        return name;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setInMST(boolean inMST) {
        this.inMST = inMST;
    }

    public boolean isInMST() {
        return inMST;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }


    public Node getParent() {
        return parent;
    }

    @Override
    public int compareTo(Node o) {
        if(Objects.equals(this.distance, o.distance)){
            return this.name.compareTo(o.name);
        }
        return this.distance-o.distance;
    }
}
